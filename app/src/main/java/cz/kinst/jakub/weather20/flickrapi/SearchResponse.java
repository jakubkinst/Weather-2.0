package cz.kinst.jakub.weather20.flickrapi;

import java.util.List;


/**
 * Created by jakubkinst on 03/12/15.
 */
public class SearchResponse {

	private PhotosEntity photos;


	private String stat;


	public PhotosEntity getPhotos() {
		return photos;
	}


	public String getStat() {
		return stat;
	}


	public static class PhotosEntity {
		private int page;
		private int pages;
		private int perpage;
		private String total;

		private List<PhotoEntity> photo;


		public int getPage() {
			return page;
		}


		public int getPages() {
			return pages;
		}


		public int getPerpage() {
			return perpage;
		}


		public String getTotal() {
			return total;
		}


		public List<PhotoEntity> getPhoto() {
			return photo;
		}


		public static class PhotoEntity {
			private String id;
			private String owner;
			private String secret;
			private String server;
			private int farm;
			private String title;
			private int ispublic;
			private int isfriend;
			private int isfamily;


			public String getUrl() {
				return String.format("https://farm%s.staticflickr.com/%s/%s_%s.jpg", farm, server, id, secret);
			}


			public String getId() {
				return id;
			}


			public String getOwner() {
				return owner;
			}


			public String getSecret() {
				return secret;
			}


			public String getServer() {
				return server;
			}


			public int getFarm() {
				return farm;
			}


			public String getTitle() {
				return title;
			}


			public int getIspublic() {
				return ispublic;
			}


			public int getIsfriend() {
				return isfriend;
			}


			public int getIsfamily() {
				return isfamily;
			}
		}
	}
}
