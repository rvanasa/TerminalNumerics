package net.anasa.util.task;

import java.io.File;
import java.io.FileFilter;

import net.anasa.util.Listing;
import net.anasa.util.Listing.IListAction;

public class DirectoryTask extends ListTask<File>
{
	private static File setupDir(File dir)
	{
		dir.mkdirs();
		
		return dir;
	}
	
	public DirectoryTask(String name, File dir, IListAction<File> action)
	{
		this(name, dir, null, action);
	}
	
	public DirectoryTask(String name, File dir, FileFilter filter, IListAction<File> action)
	{
		super(name, new Listing<>(setupDir(dir).listFiles(filter)), action);
	}
}
