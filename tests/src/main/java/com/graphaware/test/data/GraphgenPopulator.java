/*
 * Copyright (c) 2013-2020 GraphAware
 *
 * This file is part of the GraphAware Framework.
 *
 * GraphAware Framework is free software: you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software Foundation, either
 * version 3 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU General Public License for more details. You should have received a copy of
 * the GNU General Public License along with this program.  If not, see
 * <http://www.gnu.org/licenses/>.
 */

package com.graphaware.test.data;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * {@link com.graphaware.test.data.CypherPopulator} that reads Cypher statement groups from a file located on path provided
 * by {@link #file()}. The file is assumed to be generated by Graphgen, i.e. to start with schema creation statements,
 * followed by data import statements.
 */
public abstract class GraphgenPopulator extends CypherPopulator {

    /**
     * {@inheritDoc}
     */
    @Override
    protected String[] statementGroups() {
        StringBuilder schema = new StringBuilder();
        StringBuilder data = new StringBuilder();

        try {
            String file = file();
            if (file == null) {
                return null;
            }

            BufferedReader in = new BufferedReader(new FileReader(file));
            while (in.ready()) {
                String line = in.readLine();
                if (line.startsWith("CREATE CONSTRAINT") || line.startsWith("CREATE INDEX")) {
                    schema.append(line);
                } else {
                    data.append(line);
                }
            }
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new String[]{schema.toString(), data.toString()};
    }

    @Override
    protected String separator() {
        return ";";
    }

    /**
     * @return paths to file with Cypher statements.
     */
    protected abstract String file() throws IOException;
}
