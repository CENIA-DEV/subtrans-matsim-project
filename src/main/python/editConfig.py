"""
Read the sensor database and edit the configuration file using those values
"""

import os
import xml.etree.ElementTree as ET
import sqlite3
import pandas as pd
import argparse

# Main function
def main(args):
    # Read the SQL sensor database with pandas
    sql_sensor_db_path = args.sql_sensor_db_path
    table_name = args.table_name
    conn = sqlite3.connect(sql_sensor_db_path)
    vehicle_db = pd.read_sql(f'SELECT * FROM {table_name}', con=conn)

    # Read the configuration file
    scenario_config_path = args.scenario_config_path
    config_file = ET.parse(scenario_config_path)
    config_root = config_file.getroot()
    config_sensors = config_root.findall('sensor')

    # Edit the configuration file


    # Write the configuration file
    config_file.write('config.xml')


if __name__ == '__main__':
    parser = argparse.ArgumentParser(description='Edit the configuration file using the sensor database')
    parser.add_argument('sql_sensor_db_path', type=str, help='Path to the SQL sensor database')
    parser.add_argument('table_name', type=str, help='Name of the table in the SQL sensor database')
    parser.add_argument('scenario_config_path', type=str, help='Path to the scenario configuration file')
    args = parser.parse_args()
    main(args)