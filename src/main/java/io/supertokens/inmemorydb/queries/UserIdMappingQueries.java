/*
 *    Copyright (c) 2022, VRAI Labs and/or its affiliates. All rights reserved.
 *
 *    This software is licensed under the Apache License, Version 2.0 (the
 *    "License") as published by the Apache Software Foundation.
 *
 *    You may not use this file except in compliance with the License. You may
 *    obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 *    WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 *    License for the specific language governing permissions and limitations
 *    under the License.
 */

package io.supertokens.inmemorydb.queries;

import io.supertokens.inmemorydb.Start;
import io.supertokens.inmemorydb.config.Config;

public class UserIdMappingQueries {

    public static String getQueryToCreateUserIdMappingTable(Start start) {
        String tableName = Config.getConfig(start).getUserIdMappingTable();
        // @formatter:off
        return "CREATE TABLE IF NOT EXISTS " + tableName + " ("
                + "supertokens_user_id CHAR(36) NOT NULL UNIQUE,"
                + "external_user_id VARCHAR(128) NOT NULL UNIQUE,"
                + "external_user_id_info TEXT"
                + "PRIMARY KEY(supertokens_user_id, external_user_id),"
                + "FOREIGN KEY(supertokens_user_id) REFERENCES " + Config.getConfig(start).getUsersTable()
                +"(user_id) ON DELETE CASCADE );";

        // @formatter:on
    }
}