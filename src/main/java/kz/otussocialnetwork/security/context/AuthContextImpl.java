package kz.otussocialnetwork.security.context;

import org.apache.catalina.User;

public class AuthContext {

  ThreadLocal<User> userThreadLocal = new ThreadLocal<>();

}
