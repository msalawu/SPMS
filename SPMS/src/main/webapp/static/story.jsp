<head>
    <title>Jagabani SPMS Stories</title>
    <link rel="stylesheet"  href="style.css" />
</head>
<body>
    <header>
        <h1><a href="index.html">Home</a></h1>
    </header>
    <main>
        <div id="myForm">

        </div>
        <div class="form-popup" id="infoPopup">
            <div class="form-container" id="infoSection">

            </div>
        </div>
        <div id="storyDiv">
            
        </div>
        <div class="form-popup" id="storyForm">
            <form action="/action_page.php" class="form-container"><!--TODO-->
                <h2>Create a new story pitch</h2>
                
                <lable for="title">Please Enter A Story Title Below</lable>
                <input id="title" type="text" name="title" placeholder="Story Title" required="required" /><br>
                <lable for="storyType">Please Select A Sotry Type Below</lable><br>
                <select id="storyType" name="storyType">
                    <option value="Novel">Novel     -50 Points</option>
                    <option value="Novella">Novella     -25 Points</option>
                    <option value="Short Story">Short Story     -20 Points</option>
                    <option value="Article">Article     -10 Points</option>
                </select><br>
                <lable for="genre">Please Select A Genre Below</lable><br>
                <select id="genre" name="genre">        <!--soucred from https://www.oprahmag.com/entertainment/books/a29576863/types-of-book-genres/-->
                    <option value="Nonfiction">Non-Fiction</option>
                    <option value="Action Adventure">Action Adventure</option>
                    <option value="Classics">Classics</option>
                    <option value="Comic">Comic</option>
                    <option value="Fantasy">Fantasy</option>
                    <option value="Horror">Horror</option>
                    <option value="Romance">Romance</option>
                    <option value="Thriller">Thriller</option>
                    <option value="Cook Book">Cook Book</option>
                    <option value="History">History</option>
                    <option value="Poetry">Poetry</option>
                    <option value="Crime">Crime</option>
                </select><br>
                <label for="tagline">Please Enter A Tagline Below </label>
                <input type="text" placeholder="Enter a tagline" name="tagline" id="tagline" required><br>
                <label for="description">Please Enter A Brief Story Description Below </label>
                <input type="text" placeholder="Enter a story description" name="description" id="description" required><br>
                <label for="authorInfo">Please Enter Your Name Below </label>
                <input type="text" placeholder="Enter some information about the author" name="authorInfo" id="authorInfo" required><br>
                <label for="completionDate">Please Enter An Estimated Completion Date Below </label>
                <input type="date" id="completionDate" name="completionDate"><br>
                <button type="button" class="button" onclick="createStory()">Submit</button>
                <button type="button" class="button" onclick="closeCreateStory()">Cancel</button>
            </form>
        </div>
    </main>
    <script src="index.js"></script>
    <script src="story.js"></script>
</body>
