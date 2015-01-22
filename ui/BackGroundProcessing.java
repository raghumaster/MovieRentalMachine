/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mrm.ui;

import java.util.List;
import javax.swing.SwingWorker;

/**
 *
 * @author RaghuNandan
 */
public class BackGroundProcessing extends SwingWorker<Integer, String>{

    @Override
    protected Integer doInBackground() throws Exception {
        //start
        publish("start");
        setProgress(1);
        
        //More work is done
        publish("Processing...");
        setProgress(10);
               
        //complete
        publish("Sucess");
        setProgress(100);
        return 1;
    }
    
    @Override
    protected void process(List<String> chunks){
        
    }
}
