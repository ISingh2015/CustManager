
package com.custmanager.images;

import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;
import javax.swing.ImageIcon;

public class ImagesDirNew
{
	private static ImagesDirNew m_clsImagesDir;

	private ImagesDirNew() {}

	private synchronized static ImagesDirNew getInstance()
	{
		if( m_clsImagesDir == null )
		{
			m_clsImagesDir = new ImagesDirNew();
		}

		return m_clsImagesDir;
	}

	public static synchronized Image getImage( String istrAdditionalPath )
	{
		ImagesDirNew clsImagesDir = ImagesDirNew.getInstance();

		URL urlPath = clsImagesDir.getClass().getResource( istrAdditionalPath );
                Image image = Toolkit.getDefaultToolkit().getImage(urlPath);
		return image;
	}

}
