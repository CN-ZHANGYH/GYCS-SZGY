-- 菜单 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('公益灾区捐赠活动', '1', '1', 'activitieInfo', 'charity/activitieInfo/index', 1, 0, 'C', '0', '0', 'charity:activitieInfo:list', '#', 'admin', sysdate(), '', null, '公益灾区捐赠活动菜单');

-- 按钮父菜单ID
SELECT @parentId := LAST_INSERT_ID();

-- 按钮 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('公益灾区捐赠活动查询', @parentId, '1',  '#', '', 1, 0, 'F', '0', '0', 'charity:activitieInfo:query',        '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('公益灾区捐赠活动新增', @parentId, '2',  '#', '', 1, 0, 'F', '0', '0', 'charity:activitieInfo:add',          '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('公益灾区捐赠活动修改', @parentId, '3',  '#', '', 1, 0, 'F', '0', '0', 'charity:activitieInfo:edit',         '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('公益灾区捐赠活动删除', @parentId, '4',  '#', '', 1, 0, 'F', '0', '0', 'charity:activitieInfo:remove',       '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('公益灾区捐赠活动导出', @parentId, '5',  '#', '', 1, 0, 'F', '0', '0', 'charity:activitieInfo:export',       '#', 'admin', sysdate(), '', null, '');