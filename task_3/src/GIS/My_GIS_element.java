package GIS;

import Coords.MyCoords;
import Geom.Geom_element;
import Geom.My_geom_element;
import Geom.Point3D;
/**
 * This class represents a GIS element with geometric representation and meta data such as:
 * Orientation, color, string, timing...
 * @author Shilo Gilor and Amiel Liberman
 *
 */
public class My_GIS_element implements GIS_element{
	

	private My_geom_element geom;
	private My_meta_data data;
	
/**
 * 
 * @param geom the geo information of the location of the element (gps)
 * @param data the meta data such as colour orientation  and time
 * @param mAC data of point
 * @param sSID data of point
 * @param authMode data of point
 * @param channel data of point
 * @param rSSI data of point
 * @param accuracyMeters data of point
 * @param type data of point
 */
	public My_GIS_element(My_geom_element geom, My_meta_data data) {
		super();
		this.geom = geom;
		this.data = data;
	}

	@Override
	public Geom_element getGeom() {
		return geom;
	}

	@Override
	public Meta_data getData() {
		return data;
	}

	@Override
	public void translate(Point3D vec) {
		MyCoords mycoords = new MyCoords();
		geom.setMy_geom(mycoords.add(geom.getMy_geom(), vec));		
	}

	public Point3D getMy_geom() {
		return geom.getMy_geom();
	}
	public String toStringOfGISElements(String color) {
		
		return "\t<Placemark>\n" +
				"\t<name><![CDATA[" + "name" + "]]></name>\n" 
				+"<Style>"
				+ "  <IconStyle>\n" + 
				"             <color>"+color+"</color>\n" + 
				"          </IconStyle>"+
				"</Style>"+
				"<ExtendedData>\n 	     <Data name=\"data\">"
				+ "  <value>" + "data" + "</value>"
				+ "    </Data>      <Data name=\"AuthMode\">	  "
				+ " </ExtendedData>"
				+"<styleUrl>#red</styleUrl>\n"+
				"\t<Point>\n" +
				"\t\t<coordinates>"+getMy_geom().x()+","+getMy_geom().y()+","+getMy_geom().z()+ "</coordinates>\n" +
				"\t</Point>\n" +
				"\t</Placemark>\n";
	}


}
