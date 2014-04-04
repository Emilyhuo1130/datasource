package camera_manager;

import java.util.List;

import javax.jws.WebService;

@WebService
public interface CameraManagerService {
	// 添加 视频源。
	public int AddCameras(final List<CameraRecordingSet> cameras);
}
