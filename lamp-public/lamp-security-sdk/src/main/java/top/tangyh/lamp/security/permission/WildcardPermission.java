/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package top.tangyh.lamp.security.permission;

import top.tangyh.basic.utils.CollHelper;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * 通配符权限解析器
 * 支持：
 * user:*
 * user:add,update
 * <p>
 * 参考了Shiro的源码
 *
 * @author zuihou
 * @date 2020/11/27 8:48 下午
 */
public class WildcardPermission implements Serializable {

    protected static final String WILDCARD_TOKEN = "*";
    protected static final String PART_DIVIDER_TOKEN = ":";
    protected static final String SUBPART_DIVIDER_TOKEN = ",";
    private List<Set<String>> parts;

    public WildcardPermission(String wildcardString, boolean caseSensitive) {
        init(wildcardString, caseSensitive);
    }

    protected List<Set<String>> getParts() {
        return this.parts;
    }

    protected void init(String wildcardString, boolean caseSensitive) {
        if (wildcardString == null || wildcardString.trim().length() == 0) {
            throw new IllegalArgumentException("权限编码通配符字符串不能为null或空。确保权限字符串的格式正确。");
        }

        wildcardString = wildcardString.trim();
        List<String> parts = CollHelper.asList(wildcardString.split(PART_DIVIDER_TOKEN));

        this.parts = new ArrayList<>();
        for (String part : parts) {
            Set<String> subParts = CollHelper.asSet(part.split(SUBPART_DIVIDER_TOKEN));
            if (!caseSensitive) {
                subParts = lowercase(subParts);
            }
            if (subParts.isEmpty()) {
                throw new IllegalArgumentException("权限编码通配符字符串不能包含只有分隔符的部分，确保权限编码字符串的格式正确。");
            }
            this.parts.add(subParts);
        }

        if (this.parts.isEmpty()) {
            throw new IllegalArgumentException("权限编码通配符字符串不能只包含分隔符，确保权限编码字符串的格式正确。");
        }
    }

    private Set<String> lowercase(Set<String> subParts) {
        Set<String> lowerCasedSubParts = new LinkedHashSet<String>(subParts.size());
        for (String subpart : subParts) {
            lowerCasedSubParts.add(subpart.toLowerCase());
        }
        return lowerCasedSubParts;
    }

    public boolean implies(WildcardPermission wp) {
        List<Set<String>> otherParts = wp.getParts();

        int i = 0;
        for (Set<String> otherPart : otherParts) {
            // 如果此权限的部分数少于其他权限，则此权限中包含的部分数之后的所有内容都将自动隐含，因此返回true
            if (getParts().size() - 1 < i) {
                return true;
            } else {
                Set<String> part = getParts().get(i);
                if (!part.contains(WILDCARD_TOKEN) && !part.containsAll(otherPart)) {
                    return false;
                }
                i++;
            }
        }

        // 如果此权限的部分多于其他部分，则仅当所有其他部分都是通配符时才暗示它
        for (; i < getParts().size(); i++) {
            Set<String> part = getParts().get(i);
            if (!part.contains(WILDCARD_TOKEN)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder buffer = new StringBuilder();
        for (Set<String> part : parts) {
            if (buffer.length() > 0) {
                buffer.append(PART_DIVIDER_TOKEN);
            }
            buffer.append(part);
        }
        return buffer.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof WildcardPermission) {
            WildcardPermission wp = (WildcardPermission) o;
            return parts.equals(wp.parts);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return parts.hashCode();
    }

}
