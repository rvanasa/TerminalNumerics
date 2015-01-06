package net.anasa.math.module.scala;

import rchs.tsa.math.resource.module.IModuleDelegate

object ScalaModule extends IModuleDelegate {
	@Override
	def init() {
		System.out.println("Scala module loaded!");
	}
}