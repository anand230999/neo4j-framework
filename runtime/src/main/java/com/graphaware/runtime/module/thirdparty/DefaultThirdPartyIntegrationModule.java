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

package com.graphaware.runtime.module.thirdparty;

import com.graphaware.common.representation.DetachedNode;
import com.graphaware.common.representation.DetachedRelationship;
import com.graphaware.common.representation.GraphDetachedNode;
import com.graphaware.common.representation.GraphDetachedRelationship;
import com.graphaware.writer.thirdparty.ThirdPartyWriter;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;

public class DefaultThirdPartyIntegrationModule extends WriterBasedThirdPartyIntegrationModule<Long> {

    public DefaultThirdPartyIntegrationModule(String moduleId, ThirdPartyWriter writer) {
        super(moduleId, writer);
    }

    @Override
    protected DetachedRelationship<Long, ? extends DetachedNode<Long>> relationshipRepresentation(Relationship relationship) {
        return new GraphDetachedRelationship(relationship);
    }

    @Override
    protected DetachedNode<Long> nodeRepresentation(Node node) {
        return new GraphDetachedNode(node);
    }
}

