package build;

import java.io.File;

import net.anasa.util.data.format.IFormat;
import net.anasa.util.data.io.FileHandler;

public class BuildHandler
{
	public static void main(String... argv) throws Exception
	{
		FileHandler<Integer> handler = new FileHandler<>(new File("src/main/resources/build.txt"), IFormat.INT);
		
		handler.write(handler.read() + 1);
	}
}
