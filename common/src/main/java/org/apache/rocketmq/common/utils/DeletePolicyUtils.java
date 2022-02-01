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
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package org.apache.rocketmq.common.utils;

import org.apache.rocketmq.common.TopicAttributes;
import org.apache.rocketmq.common.TopicConfig;
import org.apache.rocketmq.common.attribute.DeletePolicy;

import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class DeletePolicyUtils {
    public static boolean isCompaction(Optional<TopicConfig> topicConfig) {
        return Objects.equals(DeletePolicy.COMPACTION, getDeletePolicy(topicConfig));
    }

    public static DeletePolicy getDeletePolicy(Optional<TopicConfig> topicConfig) {
        if (!topicConfig.isPresent()) {
            return DeletePolicy.valueOf(TopicAttributes.DELETE_POLICY_ATTRIBUTE.getDefaultValue());
        }

        String attributeName = TopicAttributes.DELETE_POLICY_ATTRIBUTE.getName();

        Map<String, String> attributes = topicConfig.get().getAttributes();
        if (attributes == null || attributes.size() == 0) {
            return DeletePolicy.valueOf(TopicAttributes.DELETE_POLICY_ATTRIBUTE.getDefaultValue());
        }

        if (attributes.containsKey(attributeName)) {
            return DeletePolicy.valueOf(attributes.get(attributeName));
        } else {
            return DeletePolicy.valueOf(TopicAttributes.DELETE_POLICY_ATTRIBUTE.getDefaultValue());
        }
    }
}
