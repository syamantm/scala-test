package com.schibsted.example

import netflix.adminresources.resources.KaryonWebAdminModule
import netflix.karyon.health.AlwaysHealthyHealthCheck
import netflix.karyon.servo.KaryonServoModule
import netflix.karyon.{KaryonBootstrapModule, ShutdownModule, Karyon}


/**
 * @author : syamantak
 */
object Main extends App {

  Karyon.forRequestHandler(8080, HelloHandler(),
    new KaryonBootstrapModule(new AlwaysHealthyHealthCheck),
    Karyon.toBootstrapModule(classOf[KaryonWebAdminModule]),
    ShutdownModule.asBootstrapModule,
    KaryonServoModule.asBootstrapModule
  ).startAndWaitTillShutdown
}
