package org.visminer.web.model;
import java.util.ArrayList;
import java.util.List;

import org.visminer.model.business.File;
import org.visminer.model.business.Metric;
import org.visminer.model.business.Repository;
import org.visminer.model.business.SoftwareEntity;
import org.visminer.main.Visminer;

public class MetricValue {

	private List<Metric> metrics;
	private Metric metric;
	private double greater;
	
	public MetricValue(){
		
	}
	
	public List<Metric> getMetricValue(String chosen, Visminer visminer, Repository repo){
		
		List<Metric> metrics = new ArrayList();
		
		Metric metric = new Metric();
		
		for(Metric m :  visminer.getMetrics()){
			if(m.getName() == chosen){
				metric = m;
			}
		}
		
		this.greater = 1;
		for(File f: repo.getFiles()){
			
			if(f.getSoftwareEntities().size() == 0) continue;
			
			System.out.println("entity: "+f.getSoftwareEntities().get(0).getName());
			//getting the most value from the value of the metric
			for(Metric m : f.getSoftwareEntities().get(0).getMetrics()){
				//verifies that the value of metricValue is greater than the last value is set higher and the file exists because of the LOC TAG
				if(metric.getName() == m.getName()){
					if(Double.parseDouble(m.getValue()) > this.greater){
						this.greater = Double.parseDouble(m.getValue());
					}
				}
			}
		}
		
		return metrics;
	}
	
	public double getGreater(){
		
		return this.greater;
		
	}
	
	public Metric getMetric(){
		
		return this.metric;
	}
}
