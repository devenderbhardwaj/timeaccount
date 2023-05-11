<%@page import="com.myblog.entities.User,jakarta.servlet.RequestDispatcher" %>
<!DOCTYPE html>
<html lang="en">

    <head>

        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Time Account</title>
        <link rel="stylesheet" href="css/home/style.css">
        <link rel="stylesheet" href="css/home/timelinearea.css">
        <link rel="stylesheet" href="css/home/projects.css">
        <link rel="stylesheet" href="css/home/createProject.css">
        <link rel="stylesheet" href="css/home/scriptstyle.css">
        <script src="script/home/global.js" defer></script>
        <script src="script/home/Project.js" defer></script>
        <script src="script/home/TimeLine.js" defer></script>
        <script src="script/home/sidenavswitch.js" defer></script>
        <script src="script/home/startstopfunction.js" defer></script>
        <script src="script/home/script.js" defer></script>
    </head>
    <% 
        User user = (User) session.getAttribute("user");
        {
            if (user==null) {
                RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
                rd.forward(request,response);
            }
        }
    %>

    <body>
        <!-- style.css -->
        <header>
            <span class="yellow"><b>TimeAccount</b></span>
            <span class="white"><b><%=user.getName() %></b></span>
        </header>

        <!-- style.css-->
        <nav>
            <div class="navhome activeNavbar">Home</div>
            <div class="navprojects">Projects</div>
            <div>Analytics</div>
            <div>Setting</div>
        </nav>

        <!--
            Home view
            CSS : style.css // .main 
                    home.css // Rest
        -->
        <div class="main home">
            <!--Project list; home view -->
            <ul>
                <!-- Project Names Added by JS -->
            </ul>

            <!-- Timeline in home view -->
            <div class="timelineArea">
                <!-- Timelines added by JS -->
            </div>
        </div>

        <button id="create_project_button" onclick="Project.enableCreateProjectMode()" type="button">
            +
        </button>

        <div id="create_project">
            <form>
                <input type="text" name="project_name" id="project_name" placeholder="Project Name">
                <label for="project_desc">Enter a description for project</label>
                <textarea name="project_desc" id="project_desc" rows="5" placeholder="You cannot description. this feature may be added in future" disabled></textarea>
                <input type="button" value="Save" onclick="Project.createProject()">
            </form>
        </div>
        <div class="main" id="projects">
            <div class="project">
                <div>Project 1</div>
                <div>do ghante tartalis minute</div>
            </div>
            <div class="project">
                <div>Project 1</div>
                <div>do ghante tartalis minute</div>
            </div>
        </div>


    </body>

</html>