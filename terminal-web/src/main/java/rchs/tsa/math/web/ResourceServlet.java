package rchs.tsa.math.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.anasa.util.EnumHelper;
import net.anasa.util.Listing;
import net.anasa.util.StringHelper;
import net.anasa.util.data.FormatException;
import net.anasa.util.data.io.IOHelper;
import rchs.tsa.math.resource.ResourceType;

public class ResourceServlet extends HttpServlet
{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException
	{
		PrintWriter out = resp.getWriter();
		
		try
		{
			ResourceType type = EnumHelper.getFrom(ResourceType.class, req.getParameter("type"));
			
			String id = req.getParameter("id");
			if(id != null)
			{
				File file = new File(getResourceDirectory(type), id);
				
				out.print(IOHelper.read(new FileInputStream(file)));
			}
			else
			{
				out.print(StringHelper.join("\n", new Listing<>(getResourceDirectory(type).listFiles())
						.format((file) -> file.getName())));
			}
		}
		catch(FormatException e)
		{
			throw new IOException(e);
		}
	}
	
	protected File getResourceDirectory(ResourceType type)
	{
		return new File(System.getProperty("catalina.home") + "/webapps/terminal-numerics/resources", type.getPath());
	}
}
