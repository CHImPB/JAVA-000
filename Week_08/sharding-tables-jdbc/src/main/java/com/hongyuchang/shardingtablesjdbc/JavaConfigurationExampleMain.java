/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.hongyuchang.shardingtablesjdbc;

import com.hongyuchang.shardingtablesjdbc.config.ShardingDatabasesAndTablesConfigurationPrecise;
import com.hongyuchang.shardingtablesjdbc.service.OrderService;

import javax.sql.DataSource;
import java.sql.SQLException;

/*
 * Please make sure primary replica data replication sync on MySQL is running correctly. Otherwise this example will query empty data from replica.
 */
public final class JavaConfigurationExampleMain {
    
    public static void main(final String[] args) throws SQLException {

        DataSource dataSource = new ShardingDatabasesAndTablesConfigurationPrecise().getDataSource();
        OrderService orderService = new OrderService(dataSource);
        try {
            //测试分库分表
            orderService.initEnvironment();
            orderService.processSuccess();
            //测试XA事物
            orderService.insertDataXA();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
//            orderService.cleanEnvironment();
        }
    }
}
