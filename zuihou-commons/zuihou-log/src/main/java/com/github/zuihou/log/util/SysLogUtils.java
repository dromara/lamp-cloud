///*
// *  Copyright (c) 2019-2020, 冷冷 (wangiegie@gmail.com).
// *  <p>
// *  Licensed under the GNU Lesser General Public License 3.0 (the "License");
// *  you may not use this file except in compliance with the License.
// *  You may obtain a copy of the License at
// *  <p>
// * https://www.gnu.org/licenses/lgpl.html
// *  <p>
// * Unless required by applicable law or agreed to in writing, software
// * distributed under the License is distributed on an "AS IS" BASIS,
// * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// * See the License for the specific language governing permissions and
// * limitations under the License.
// */
//
//package com.github.zuihou.log.util;
//
//import java.util.Objects;
//
//import javax.servlet.http.HttpServletRequest;
//
//import com.github.zuihou.authority.entity.common.Log;
//
//import cn.hutool.core.util.URLUtil;
//import cn.hutool.http.HttpUtil;
//import lombok.experimental.UtilityClass;
//import org.springframework.web.context.request.RequestContextHolder;
//import org.springframework.web.context.request.ServletRequestAttributes;
//
//
///**
// * 系统日志工具类
// *
// * @author zuihou
// * @date 2019-07-01 15:15
// */
//@UtilityClass
//public class SysLogUtils {
//    public Log getSysLog() {
//        HttpServletRequest request = ((ServletRequestAttributes) Objects
//                .requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
//		Log sysLog = new Log();
////		sysLog.setCreateBy(Objects.requireNonNull(getUsername()));
////		sysLog.setType(CommonConstants.STATUS_NORMAL);
////		sysLog.setRemoteAddr(ServletUtil.getClientIP(request));
//		sysLog.setRequestUri(URLUtil.getPath(request.getRequestURI()));
////		sysLog.setMethod(request.getMethod());
////		sysLog.setUserAgent(request.getHeader("user-agent"));
//		sysLog.setParams(HttpUtil.toParams(request.getParameterMap()));
////		sysLog.setServiceId(getClientId());
//		return sysLog;
//    }
//
//
//    /**
//     * 获取客户端
//     *
//     * @return clientId
//     */
////	private String getClientId() {
////		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
////		if (authentication instanceof OAuth2Authentication) {
////			OAuth2Authentication auth2Authentication = (OAuth2Authentication) authentication;
////			return auth2Authentication.getOAuth2Request().getClientId();
////		}
////		return null;
////	}
//
////	/**
////	 * 获取用户名称
////	 *
////	 * @return username
////	 */
////	private String getUsername() {
////		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
////		if (authentication == null) {
////			return null;
////		}
////		return authentication.getName();
////	}
//
//}
