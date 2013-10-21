/*
 * ==========================================
 * JGraphT : a free Java graph-theory library
 * ==========================================
 * 
 * Project Info: http://jgrapht.sourceforge.net/
 * Project Creator: Barak Naveh (http://sourceforge.net/users/barak_naveh)
 * 
 * (C) Copyright 2003-2008, by Barak Naveh and Contributors.
 * 
 * This library is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation; either version 2.1 of the License, or
 * (at your option) any later version.
 * 
 * This library is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
 * or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public
 * License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with this library; if not, write to the Free Software Foundation,
 * Inc.,
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307, USA.
 */
/*
 * ---------------------
 * GraphChangeEvent.java
 * ---------------------
 * (C) Copyright 2003-2008, by Barak Naveh and Contributors.
 * 
 * Original Author: Barak Naveh
 * Contributor(s): -
 * 
 * $Id: GraphChangeEvent.java 645 2008-09-30 19:44:48Z perfecthash $
 * 
 * Changes
 * -------
 * 10-Aug-2003 : Initial revision (BN);
 */
package org.jgrapht.event;


import java.util.*;


/**
 * An event which indicates that a graph has changed. This class is a root for
 * graph change events.
 * 
 * @author Barak Naveh
 * @since Aug 10, 2003
 */
public class GraphChangeEvent extends EventObject
{
	// ~ Static fields/initializers ---------------------------------------------

	private static final long	serialVersionUID	= 3834592106026382391L;

	// ~ Instance fields --------------------------------------------------------

	/**
	 * The type of graph change this event indicates.
	 */
	protected int				type;


	// ~ Constructors -----------------------------------------------------------

	/**
	 * Creates a new graph change event.
	 * 
	 * @param eventSource the source of the event.
	 * @param type the type of event.
	 */
	public GraphChangeEvent (Object eventSource, int type)
	{
		super (eventSource);
		this.type = type;
	}


	// ~ Methods ----------------------------------------------------------------

	/**
	 * Returns the event type.
	 * 
	 * @return the event type.
	 */
	public int getType()
	{
		return type;
	}
}

// End GraphChangeEvent.java
