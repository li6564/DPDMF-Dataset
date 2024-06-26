/**
 * Java Diagram Package; An extremely flexible and fast multipurpose diagram
 * component for Swing.
 * Copyright (C) 2001  Eric Crahen <crahen@cse.buffalo.edu>
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 */
package util;
/**
 *
 * @class ClassFilter
 * @date 08-20-2001
 * @author Eric Crahen
 * @version 1.0

Filter class for a FilteredIterator. This will filter out objects
that are not assignable to a particular class.
 */
public class ClassFilter implements java.lang.Comparable {
    private java.lang.Class filterClass;

    /**
     * Create a new ClassFilter
     *
     * @param Class
     */
    public ClassFilter(java.lang.Class c) {
        filterClass = c;
    }

    /**
     * Compare an item to this filter.
     *
     * @param Object
     * @return int
     */
    public int compareTo(java.lang.Object o) {
        if (o == null)
            return -1;

        return filterClass.isAssignableFrom(o.getClass()) ? 0 : 1;
    }
}