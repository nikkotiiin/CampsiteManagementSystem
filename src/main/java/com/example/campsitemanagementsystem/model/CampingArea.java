package com.example.campsitemanagementsystem.model;
import com.example.campsitemanagementsystem.structures.NodeList;

public class CampingArea {
    private String id;
    private String description;
    private NodeList<Plot> plots;


    public CampingArea(String id, String description){
        this.id=id;
        this.description=description;
        this.plots=new NodeList<Plot>();
    }
    public String getId() {
        return id;
    }
    public String getDescription(){
        return description;
    }

    public void setId(String id) {
        this.id = id;
    }
    public void addPlot(Plot plot){
        for(int i=0; i<getPlotCount(); i++){
            Plot currentPlot = getPlot(i);
            if(currentPlot.getId().equals(plot.getId())){
                return;
            }
        }
        plots.add(plot);
    }
    public int getPlotCount(){
        return plots.size();
    }
    public Plot getPlot(int index){
        return plots.getElement(index);
    }
    public Plot findMatchingPlot(Booking booking) {
        for (int i = 0; i < getPlotCount(); i++){
            Plot currentPlot = getPlot(i);
            if(booking.isPlotMatching(currentPlot)){
                return currentPlot;
            }
        }
        return null;

    }
}
