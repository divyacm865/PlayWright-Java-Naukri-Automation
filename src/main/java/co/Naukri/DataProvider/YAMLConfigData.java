package co.Naukri.DataProvider;

import java.util.List;

public class YAMLConfigData<UserConfig> {

	// 2. Changed name to "users" to match your YAML key. Made it a List to accept
	// multiple entries.
	private static List<UserConfig> users;

	// 3. Setters and getters must match standard naming conventions exactly
	// (getUsers / setUsers)
	public List<UserConfig> getUsers() {
		return users;
	}

	public void setUsers(List<UserConfig> users) {
		this.users = users;
	}

	public static class UserConfig {
		private String username;
		private String password;

		public String getUsername() {
			return username;
		}

		public void setUsername(String username) {
			this.username = username;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public UserConfig get(int i) {
			return users.get(i);
		}
	}
}