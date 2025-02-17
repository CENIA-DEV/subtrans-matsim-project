/* *********************************************************************** *
 * project: org.matsim.*												   *
 *                                                                         *
 * *********************************************************************** *
 *                                                                         *
 * copyright       : (C) 2008 by the members listed in the COPYING,        *
 *                   LICENSE and WARRANTY file.                            *
 * email           : info at matsim dot org                                *
 *                                                                         *
 * *********************************************************************** *
 *                                                                         *
 *   This program is free software; you can redistribute it and/or modify  *
 *   it under the terms of the GNU General Public License as published by  *
 *   the Free Software Foundation; either version 2 of the License, or     *
 *   (at your option) any later version.                                   *
 *   See also COPYING, LICENSE and WARRANTY file                           *
 *                                                                         *
 * *********************************************************************** */
package org.matsim.project;

// import com.google.inject.internal.asm.$Type;
// import org.matsim.api.core.v01.Id;
import org.matsim.api.core.v01.Scenario;
import org.matsim.contrib.roadpricing.RoadPricingConfigGroup;
import org.matsim.contrib.roadpricing.RoadPricingModule;
// import org.matsim.api.core.v01.TransportMode;
// import org.matsim.api.core.v01.network.Link;
// import org.matsim.contrib.otfvis.OTFVisLiveModule;
import org.matsim.core.config.Config;
import org.matsim.core.config.ConfigUtils;
// import org.matsim.core.config.groups.PlanCalcScoreConfigGroup;
// import org.matsim.core.config.groups.PlansCalcRouteConfigGroup;
// import org.matsim.core.config.groups.QSimConfigGroup;
// import org.matsim.core.config.groups.QSimConfigGroup.SnapshotStyle;
// import org.matsim.core.config.groups.QSimConfigGroup.TrafficDynamics;
// import org.matsim.core.config.groups.QSimConfigGroup.VehiclesSource;
// import org.matsim.core.config.groups.StrategyConfigGroup;
import org.matsim.core.controler.Controler;
// import org.matsim.core.controler.OutputDirectoryHierarchy;
import org.matsim.core.controler.OutputDirectoryHierarchy.OverwriteFileSetting;
// import org.matsim.core.replanning.strategies.DefaultPlanStrategiesModule;
import org.matsim.core.scenario.ScenarioUtils;
// import org.matsim.core.utils.collections.CollectionUtils;
// import org.matsim.vehicles.VehicleType;
// import org.matsim.vehicles.VehicleUtils;
// import org.matsim.vis.otfvis.OTFVisConfigGroup;

// import java.util.ArrayList;
// import java.util.Collection;
// import java.util.Set;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


/**
 * @author nagel
 *
 */
public class RunMatsim{

	private static final Logger logger = LogManager.getLogger(RunMatsim.class);
	public static void main(String[] args) {
		// --- Print current directory
		logger.info("Current directory: " + System.getProperty("user.dir"));

		// --- Load the config file
		Config config;
		if ( args==null || args.length==0 || args[0]==null ){
			config = ConfigUtils.loadConfig( "scenarios/equil/config.xml" );
		} else {
			config = ConfigUtils.loadConfig( args );
		}
		// --- Print out the config file
        // logger.info(config.toString());

		// --- Exit the program
		// System.exit(0);

		config.controler().setOverwriteFileSetting( OverwriteFileSetting.deleteDirectoryIfExists );

		// possibly modify config here

		// ---

		Scenario scenario = ScenarioUtils.loadScenario(config) ;

		// possibly modify scenario here

		// ---

		Controler controler = new Controler( scenario ) ;

		// possibly modify controler here

		// --- Add road pricing module if road pricing file is present
		// RoadPricingConfigGroup rpcg = ConfigUtils.addOrGetModule( scenario.getConfig(), RoadPricingConfigGroup.class );
		// if ( rpcg.getTollLinksFile()!=null && !rpcg.getTollLinksFile().equals( "" ) ){
		// 	// found road pricing file, so am switching on road pricing:
		// 	controler.addOverridingModule( new RoadPricingModule() );
		// }


		// controler.addOverridingModule( new OTFVisLiveModule() ) ;

		// controler.addOverridingModule( new SimWrapperModule() );

		// ---

		controler.run();
	}

}
