/*
 * JBoss, Home of Professional Open Source
 * Copyright 2005, JBoss Inc., and individual contributors as indicated
 * by the @authors tag. See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */
package org.jbpm.examples.mail;

import junit.extensions.TestSetup;
import junit.framework.Test;
import junit.framework.TestSuite;

import org.jbpm.AbstractJbpmTestCase;
import org.jbpm.JbpmConfiguration;
import org.jbpm.JbpmContext;
import org.jbpm.graph.def.ProcessDefinition;
import org.jbpm.graph.exe.ProcessInstance;
import org.jbpm.mail.AddressResolver;

import com.dumbster.smtp.SimpleSmtpServer;

public class MailTest extends AbstractJbpmTestCase {

  private static SimpleSmtpServer server;

  private static final String XML_DECL = "<?xml version='1.0'?>";

  public static Test suite() {
    return new TestSetup(new TestSuite(MailTest.class)) {
      protected void setUp() throws Exception {
        server = startSmtpServer(23583);
      }

      protected void tearDown() throws Exception {
        server.stop();
      }
    };
  }

  public void testSimpleProcess() throws Exception {
    JbpmConfiguration jbpmConfiguration = JbpmConfiguration.parseXmlString(XML_DECL +
        "<jbpm-configuration>" +
        "  <jbpm-context />" +
        "  <string name='resource.mail.properties' value='mail/mail.properties' />" +
        "  <bean name='jbpm.mail.address.resolver' class='" +
        MyAddressResolver.class.getName() +
        "' singleton='true' />" +
        "</jbpm-configuration>");

    JbpmContext jbpmContext = jbpmConfiguration.createJbpmContext();
    try {
      ProcessDefinition processDefinition = ProcessDefinition.parseXmlString(XML_DECL +
          "<process-definition name='mailtest'>" +
          "  <start-state name='start'>" +
          "    <transition to='start toothpick line' />" +
          "  </start-state>" +
          "  <task-node name='start toothpick line'>" +
          "    <task notify='yes'>" +
          "      <assignment actor-id='grandma' />" +
          "    </task>" +
          "    <transition to='end' />" +
          "  </task-node>" +
          "  <end-state name='end' />" +
          "</process-definition>");
      ProcessInstance processInstance = new ProcessInstance(processDefinition);
      processInstance.signal();
      jbpmContext.save(processInstance);
    }
    finally {
      jbpmContext.close();
    }

    assertEquals(1, server.getReceivedEmailSize());
  }

  static SimpleSmtpServer startSmtpServer(int port) {
    /*
     * SimpleSmtpServer.start(int) blocks the calling thread until the server socket is created. If
     * the socket is created too quickly (happens on Linux and Mac) then the notification is sent
     * too early and the calling thread blocks forever.
     * 
     * The code below corresponds to SimpleSmtpServer.start(int) except that the thread is started
     * inside of the synchronized block.
     */
    SimpleSmtpServer server = new SimpleSmtpServer(port);
    Thread serverThread = new Thread(server);

    // Block until the server socket is created
    synchronized (server) {
      serverThread.start();
      try {
        server.wait(10 * 1000);
      }
      catch (InterruptedException e) {
        // Ignore don't care.
      }
    }
    return server;
  }

  public static class MyAddressResolver implements AddressResolver {
    private static final long serialVersionUID = 1L;

    public Object resolveAddress(String actorId) {
      return actorId + "@dalton.com";
    }
  }
}
