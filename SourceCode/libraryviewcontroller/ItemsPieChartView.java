/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package libraryviewcontroller;
/**
 *
 * @author Silpa
 */

import java.awt.*;
import java.util.*;
import java.util.List;
import javax.swing.*;

class PieItem {
        double item;
        String name;
        double count;
        Color color;

        public PieItem(double item, String name, double count, Color color) {
          this.item = item;
          this.name = name;
          this.count = count;
          this.color = color;
        }
}
/* This class creates a pie chart */
public class ItemsPieChartView extends JPanel {
            // Set of observed Items
            private List items = new ArrayList();

            // Map of Colors for drawing pie chart wedges
            private Map colors = new HashMap();

            // add Item to pie chart view
            public void addItem( double item, String name, double count, Color color )
            {  
                    PieItem pitem = new PieItem(item, name, count, color);
                    // add Item to item Vector
                    items.add( pitem );
                      // add Color to Hashtable for drawing Item's wedge
                    colors.put( pitem, pitem.color );
                      // update display with new Item information
                    repaint();
            }

            // remove Item from pie chart view
            public void removeItem( PieItem pitem )
            {
                    // remove Item from items Vector
                    items.remove( pitem );
                    // remove Item's Color from Hashtable
                    colors.remove( pitem );
                    // update display to remove Item information
                    repaint();
            }

            // draw Item counts in a pie chart
            public void paintComponent( Graphics g )
            {
                    // ensure proper painting sequence
                    super.paintComponent( g );

                    // draw pie chart
                    drawPieChart( g );

                    // draw legend to describe pie chart wedges
                    drawLegend( g );
            }

            // draw pie chart on given Graphics context
            private void drawPieChart( Graphics g )
            {
                    // get combined Item count
                    double totalCount = getTotalCount();

                    // create temporary variables for pie chart calculations
                    double percentage = 0.0;
                    int startAngle = 0;
                    int arcAngle = 0;

                    Iterator itemIterator = items.iterator();
                    PieItem pitem = null;

                    // draw pie wedge for each Item
                    while (itemIterator.hasNext()) {

                       // get next Item from Iterator
                       pitem = (PieItem) itemIterator.next();

                       // draw wedges only for included Items
                       if (!includeItemInChart(pitem))
                          continue;

                       // get percentage of total count per Item
                       percentage = pitem.count / totalCount;

                       // calculate arc angle for percentage
                       arcAngle = (int) Math.round(percentage * 360);

                       // set drawing Color for Item pie wedge
                       g.setColor((Color)colors.get(pitem));

                       // draw Items pie wedge
                       g.fillArc( 5, 5, 100, 100, startAngle, arcAngle );

                       // calculate startAngle for next pie wedge
                       startAngle += arcAngle;
                    }
            } // end method drawPieChart

            // draw pie chart legend on given Graphics context
            private void drawLegend( Graphics g )
            {
                    Iterator itemIterator = items.iterator();
                    PieItem pitem = null;

                    // create Font for Item name
                    Font font = new Font( "SansSerif", Font.BOLD, 12 );
                    g.setFont( font );

                    // get FontMetrics for calculating offsets and
                    // positioning descriptions
                    FontMetrics metrics = getFontMetrics( font );
                    int ascent = metrics.getMaxAscent();
                    int offsetY = ascent + 2;

                    // draw description for each Item
                    for ( int i = 1; itemIterator.hasNext(); i++ ) {

                       // get next Item from Iterator
                       pitem = (PieItem)itemIterator.next();

                       // draw Item color swatch at next offset
                       g.setColor( ( Color ) colors.get( pitem ) );
                       g.fillRect( 125, offsetY * i, ascent, ascent );

                       // draw Item name next to color swatch
                       g.setColor( Color.black );
                       g.drawString( pitem.name, 140,
                          offsetY * i + ascent );
                    }
                 } // end method drawLegend

                 // get combined count of all observed Items
                 private double getTotalCount()
                 {
                    int sum = 0;

                    Iterator itemIterator = items.iterator();
                    PieItem pitem = null;

                    // calculate total count
                    while (itemIterator.hasNext()) {
                       pitem = (PieItem)itemIterator.next();

                       // add only included Items to sum
                       if (includeItemInChart(pitem))
                          sum += pitem.count;
                    }

                    return sum;
                 }

                 // set Item value
                 public void setCount(int item, double count)
                 {
                    Iterator itemIterator = items.iterator();
                    PieItem pitem = null;

                    // calculate total count
                    while (itemIterator.hasNext()) {
                       pitem = (PieItem)itemIterator.next();

                       // add only included Items to sum
                       if (pitem.item == item)
                          pitem.count = count;
                    }
            }

            // return true if given Item should be included in
            // pie chart
            protected boolean includeItemInChart(PieItem pitem)
            {
                    // include only Items with count
                    return pitem.count > 0.0;
            }

            // get ItemsPieChartView's preferred size
            public Dimension getPreferredSize()
            {
                    return new Dimension( 250, 200 );
            }

            // get ItemsPieChartView's preferred size
            public Dimension getMinimumSize()
            {
                    return getPreferredSize();
            }

            // get ItemsPieChartView's preferred size
            public Dimension getMaximumSize()
            {
                    return getPreferredSize();
            }
}


