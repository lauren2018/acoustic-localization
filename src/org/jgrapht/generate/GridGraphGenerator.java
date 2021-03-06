/*
 * ==========================================
 * JGraphT : a free Java graph-theory library
 * ==========================================
 * 
 * Project Info: http://jgrapht.sourceforge.net/
 * Project Creator: Barak Naveh (http://sourceforge.net/users/barak_naveh)
 * 
 * (C) Copyright 2003-2011, by Barak Naveh and Contributors.
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
 * -------------------
 * StarGraphGenerator.java
 * -------------------
 * (C) Copyright 2011-2011, by Assaf Mizrachi and Contributors.
 * 
 * Original Author: Assaf Mizrachi
 * Contributor(s): -
 * 
 * $Id: StarGraphGenerator.java 651 2008-12-24 21:13:41Z perfecthash $
 * 
 * Changes
 * -------
 * 3-Jan-2011 : Initial revision (AM);
 */
package org.jgrapht.generate;


import java.util.*;


import org.jgrapht.*;


/**
 * Generates a bidirectional <a
 * href="http://mathworld.wolfram.com/GridGraph.html">grid graph</a> of any
 * size. A grid graph is a two dimensional graph whose vertices correspond to
 * the points in the plane with integer coordinates, x-coordinates being in the
 * range 0,..., n, y-coordinates being in the range 1,...m, and two vertices are
 * connected by an edge whenever the corresponding points are at distance 1.
 * Vertices are created from left to right and from top to bottom.
 * 
 * @author Assaf Mizrachi
 * @since Dec 29, 2010
 */
public class GridGraphGenerator<V, E> implements GraphGenerator<V, E, V>
{
	// ~ Static fields/initializers ---------------------------------------------

	/**
	 * Role for the vertices at the corners.
	 */
	public static final String	CORNER_VERTEX	= "Corner Vertex";

	// ~ Instance fields --------------------------------------------------------

	private int					rows;

	private int					cols;


	// ~ Constructors -----------------------------------------------------------

	/**
	 * Creates a new GridGraphGenerator object with rows x cols dimension.
	 * 
	 * @param rows the number of rows
	 * @param cols the number of columns
	 */
	public GridGraphGenerator (int rows, int cols)
	{
		if (rows < 2)
		{
			throw new IllegalArgumentException ("illegal number of rows (" + rows + "). there must be at least two.");
		}
		if (cols < 2)
		{
			throw new IllegalArgumentException ("illegal number of columns (" + cols + "). there must be at least two.");
		}
		this.rows = rows;
		this.cols = cols;
	}


	// ~ Methods ----------------------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@ Override
	public void generateGraph(Graph<V, E> target, VertexFactory<V> vertexFactory, Map<String, V> resultMap)
	{
		Map<Integer, V> map = new TreeMap<Integer, V> ();

		// Adding all vertices to the set
		int cornerCtr = 0;
		for (int i = 0; i < (rows * cols); i++)
		{
			V vertex = vertexFactory.createVertex ();
			target.addVertex (vertex);
			map.put (i + 1, vertex);

			boolean isCorner = (i == 0) || (i == (cols - 1)) || (i == (cols * (rows - 1))) || (i == ((rows * cols) - 1));
			if (isCorner && (resultMap != null))
			{
				resultMap.put (CORNER_VERTEX + ' ' + ++cornerCtr, vertex);
			}
		}

		// Iterating twice over the key set, for undirected graph edges are
		// added from upper vertices to lower, and from left to right. The
		// second addEdge call will return nothing; it will not add a the edge
		// at the opposite direction. For directed graph, edges in opposite
		// direction are also added.
		for (int i : map.keySet ())
		{
			for (int j : map.keySet ())
			{
				if ((((i % cols) > 0) && ((i + 1) == Integer.valueOf (j))) || ((i + cols) == j))
				{
					target.addEdge (map.get (i), map.get (j));
					target.addEdge (map.get (j), map.get (i));
				}
			}
		}
	}
}

// End GridGraphGenerator.java
