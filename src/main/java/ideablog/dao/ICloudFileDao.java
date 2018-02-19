package ideablog.dao;

import ideablog.model.CloudFile;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICloudFileDao {

    CloudFile selectCloudFileById(long id);
    List<CloudFile> selectAllCloudFiles();
    List<CloudFile> selectCloudFilesByUserId(long userId);
    List<CloudFile> selectFollowFilesByUserId(long userId);
    Boolean insertCloudFiles(@Param("userId") Long userId, @Param("fileName") String fileName, @Param("fileSize") String fileSize, @Param("filePath") String filePath, @Param("upTime") String upTime);
    Boolean switchStatusById(@Param("id") long id, @Param("status") int status);
    Boolean deleteCloudFileById(long id);
}
