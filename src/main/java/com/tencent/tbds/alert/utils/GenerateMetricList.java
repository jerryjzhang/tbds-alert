package com.tencent.tbds.alert.utils;

import org.influxdb.InfluxDB;
import org.influxdb.InfluxDBFactory;
import org.influxdb.dto.Query;
import org.influxdb.dto.QueryResult;

import java.util.Iterator;
import java.util.List;

/**
 * Created by jerryjzhang on 2016/3/16.
 */
public class GenerateMetricList {
    public static void main(String [] args)throws Exception{
        if (args.length < 3) {
            System.out.println("<influxdb_url> <influxdb_user> <influxdb_passowrd>");
            System.exit(1);
        }

        InfluxDB influxDB = InfluxDBFactory.connect(args[0], args[1], args[2]);
        QueryResult rs = influxDB.query(new Query("SHOW MEASUREMENTS", "tbds"));
        for(QueryResult.Result r : rs.getResults()){
            for(QueryResult.Series s : r.getSeries()){
                Iterator<List<Object> > itr = s.getValues().iterator();
                while(itr.hasNext()){
                    String metricName = (String)itr.next().get(0);
                    String [] items = metricName.split("_", 2);
                    if(items.length > 1) {
                        System.out.println(items[0] + "\t" + items[1]);
                    }
                }
            }
        }
    }
}
