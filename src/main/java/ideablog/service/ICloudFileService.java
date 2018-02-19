package ideablog.service;

import ideablog.model.CloudFile;

import java.util.List;

public interface ICloudFileService {

    CloudFile selectCloudFileById(long id);
    List<CloudFile> selectAllCloudFiles();
    List<CloudFile> selectCloudFilesByUserId(long userId);
    List<CloudFile> selectFollowFilesByUserId(long userId);
    Boolean insertCloudFiles(Long userId, String fileName, String fileSize, String filePath, String upTime);
    Boolean switchStatusById(long id, int status);
    Boolean deleteCloudFileById(long id);
}