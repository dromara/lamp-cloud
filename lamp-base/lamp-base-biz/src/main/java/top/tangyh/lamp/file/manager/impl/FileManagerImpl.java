package top.tangyh.lamp.file.manager.impl;

import org.springframework.stereotype.Service;
import top.tangyh.basic.base.manager.impl.SuperManagerImpl;
import top.tangyh.lamp.file.entity.File;
import top.tangyh.lamp.file.manager.FileManager;
import top.tangyh.lamp.file.mapper.FileMapper;

/**
 * 文件
 *
 * @author zuihou
 * @date 2021/10/31 10:24
 */
@Service
public class FileManagerImpl extends SuperManagerImpl<FileMapper, File> implements FileManager {

}
