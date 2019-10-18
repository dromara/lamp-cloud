#缓存规则说明：
对象缓存规则：



## 资源
公有资源：
resource:{ID} -> obj

# 菜单
公有菜单： 
menu:{ID} -> obj

# 角色
公有角色：
role:{ID} -> obj

角色拥有那些菜单：
role:menu:{ROLE_ID} -> [MENU_ID, MENU_ID, ...]

角色拥有那些资源：
role:resource:{ROLE_ID} -> [RESOURCE_ID, ...]

角色拥有那些组织：
role:org:{ROLE_ID} -> [ORG_ID, ...]

# 用户：
用户拥有那些角色：
user:role:{USER_ID} -> [ROLE_ID, ...]

用户拥有的菜单：
user:menu:{USER_ID} -> [MENU_ID, MENU_ID, ...]
查询流程：
先在缓存中查询list，
 若缓存中不存在：就查数据库，并转换为list后存到缓存，然后直接转换成tree结构返回
 若缓存中存在：  就将编译缓存中的list，查询实体数据，然后转换成tree结构返回

用户拥有的资源：
user:resource:{USER_ID} -> [RESOURCE_ID, ...]
