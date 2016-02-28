package servlets;

import accounts.AccountService;
import accounts.UserProfile;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UsersServlet extends HttpServlet {
    @SuppressWarnings({"FieldCanBeLocal", "UnusedDeclaration"})
    private final AccountService accountService;

    public UsersServlet(AccountService accountService) {
        this.accountService = accountService;
    }

    //get public user profile
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserProfile profile = accountService.getUserByLogin(request.getParameter("login"));
        UserProfile sessionId = accountService.getUserBySessionId(request.getRequestedSessionId());
        if ((profile.getLogin()).equals("admin")
                ||(profile.getPass()).equals("admin")
                ||(profile.getEmail()).equals("admin") ) {

            String json = getJsonString(sessionId);

            response.setContentType("text/html;charset=utf-8");
            response.getWriter().println(json);
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            response.setContentType("text/html;charset=utf-8");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }
    }

    //sign up
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String pass = request.getParameter("pass");
        String email = request.getParameter("email");

        if (login == null || pass == null || email == null) {
            response.setContentType("text/html;charset=utf-8");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        UserProfile newUser = new UserProfile(login, pass, email);
        accountService.addNewUser(newUser);
        accountService.addSession(request.getSession().getId(),newUser);

        String json = getJsonString(newUser);

        response.setContentType("text/html;charset=utf-8");
        response.getWriter().println(json);
        response.setStatus(HttpServletResponse.SC_OK);;
    }

    //change profile
    public void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        UserProfile userProfile = accountService.getUserByLogin(request.getParameter("login"));
        UserProfile userBySessionId = accountService.getUserBySessionId(request.getRequestedSessionId());

        userBySessionId.setLogin(request.getParameter("login"));
        userBySessionId.setPass(request.getParameter("pass"));
        userBySessionId.setEmail(request.getParameter("email"));
        accountService.updateUser(userBySessionId);

        UserProfile updatedUserProfile = accountService.getUserByLogin(request.getParameter("login"));
        String json = getJsonString(updatedUserProfile);

        response.setContentType("text/html;charset=utf-8");
        response.getWriter().println(json);
        response.setStatus(HttpServletResponse.SC_OK);;
    }

    //unregister
    public void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String user = request.getParameter("login");
        String sessionId = request.getRequestedSessionId();
        accountService.deleteSession(sessionId);
        accountService.deleteUser(user);

        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_GONE);;

        String result = String.format("User %s was deleted.", user);
        response.getWriter().println(result);

        System.out.println(result);
    }

    private String getJsonString(UserProfile sessionId) {
        Gson gson = new Gson();
        return gson.toJson(sessionId);
    }
}
