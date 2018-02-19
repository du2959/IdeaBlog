package ideablog.service.impl;

import ideablog.dao.ICloudFileDao;
import ideablog.model.CloudFile;
import ideablog.service.ICloudFileService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("cloudFileService")
public class CloudFileServiceImpl implements ICloudFileService {

    @Resource
    private ICloudFileDao cloudFileDao;

    @Override
    public CloudFile selectCloudFileById(long id) {
        return this.cloudFileDao.selectCloudFileById(id);
    }

    @Override
    public List<CloudFile> selectAllCloudFiles() {
        return this.cloudFileDao.selectAllCloudFiles();
    }

    @Override
    public List<CloudFile> selectCloudFilesByUserId(long userId) {
        return this.cloudFileDao.selectCloudFilesByUserId(userId);
    }

    @Override
    public List<CloudFile> selectFollowFilesByUserId(long userId) {
        return this.cloudFileDao.selectFollowFilesByUserId(userId);
    }

    @Override
    public Boolean insertCloudFiles(Long userId, String fileName, String fileSize, String filePath, String upTime) {
        return this.cloudFileDao.insertCloudFiles(userId, fileName, fileSize, filePath, upTime);
    }

    @Override
    public Boolean switchStatusById(long id, int status) {
        return this.cloudFileDao.switchStatusById(id, status);
    }

    @Override
    public Boolean deleteCloudFileById(long id) {
        return this.cloudFileDao.deleteCloudFileById(id);
    }
}