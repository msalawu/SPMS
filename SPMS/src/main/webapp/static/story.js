//let addStoryMenuOpen = false;
setup();

function setup() {
    checkLogin().then(() => {
        getStory();
    })
}

async function getStory() {
    let url = spmsUrl;
    if (loggedInUser.role.id == 1) {
        url += '/author';
    } else {
        url += '/editor';
    }
    
    let response = await fetch(url, {method:'GET'});
    if (response.status == 200) {
        let story = await response.json();
        getStory(story);
    }
}

async function getStoryById(id) {
    let url = spmsUrl + '/editor/' + id;

    let response = await fetch(url, {method:'GET'});
    if (response.status == 200) {
        let story = await response.json();
        return story;
    } else {
        console.log("something went wrong");
    }
}

function getStory(stories) {
    let storyDiv = document.getElementById("storyDiv");
    storyDiv.innerHTML = "";
    if (loggedInUser.role.id == 1) {
        storyDiv.innerHTML = `
            <button type="button" class="button" id="newStoryBtn" onclick="createStoryPopup()">Create A New Story</button>
        `;
    }

    if(stories != null && stories.length > 0) {
        let table = document.createElement('table');
        table.id = 'storyTable';

        table.innerHTML = `
            <tr>
                <th>ID</th>
                <th>Title</th>
                <th>Author ID</th>
                <th>Story Type</th>
                <th>Genre ID</th>
                <th>Tag Line</th>
                <th>Completion Date</th>
                <th>Status Id</th>
                <th>Story Description</th>
            </tr>
        `;

        for (let story of stories) {
            let tr = document.createElement('tr');
            tr.innerHTML = `
                <td>${story.id}</td>
                <td>${story.title}</td>
                <td>${story.author.id}</td>
                <td>${story.story.type}</td>
                <td>${story.genre.id}</td>
                <td>${story.tagline}</td>
                <td>${story.completionDate}</td>
                <td>${story.status.id}</td>
                <td>${story.description}</td>
            `;
            let infoBtn = document.createElement('button');
            infoBtn.type = 'button';
            infoBtn.classList.add('button');
            infoBtn.id = story.title + '_';
            infoBtn.textContent = 'More info';
            
            let btnTd = document.createElement('td');
            btnTd.appendChild(infoBtn);
            tr.appendChild(btnTd);
            table.appendChild(tr);

            infoBtn.addEventListener('click', function(){storyInfo(story.id);});
        }

        storyDiv.appendChild(table);
    } else {
        if (loggedInUser.role.id == 1) {
            storyDiv.innerHTML += 'You have no active stories';
        } else {
            storyDiv.innerHTML += 'No pending stories';
        }
    }
}

function createStoryPopup() {
    document.getElementById("storyForm").style.display = "block";
}

async function createStory() {
    let story = {};
    story.title = document.getElementById("storyTitle").value;
    story.type = document.getElementById("storyType").value;
    story.genre = document.getElementById("genre").value;
    story.tagline = document.getElementById("tagline").value;
    story.description = document.getElementById("description").value;
    story.authorInfo = document.getElementById("authorInfo").value;
    story.completionDate = document.getElementById("completionDate").value;
    story.pendingDate = new Date().toISOString().split("T")[0];

    let url = spmsUrl + '/author';
    let response = await fetch(url, {method:'POST', body:JSON.stringify(story)});

    switch (response.status) {
        case 201: // success
            let stories = await response.json();
            closeStoryCreate();
            populateStories(stories);
            break;
        default:
            alert('Something went wrong.');
            break;
    }
}

function closeCreateStory() {
    document.getElementById("storyForm").style.display = "none";
}

function infoClose() {
    let infoSection = document.getElementById("infoPopup");
    infoSection.style.display = "none";
}

function deleteStory() {
    // TODO
}

async function sendInfoRequest(storyId) {
    let story= await getStoryById(storyId);
    let outRequest = document.getElementById("outRequest").value;
    let infoRequest = {};
    infoRequest.id = 0;
    infoRequest.requestText = outRequest;
    infoRequest.responseText = '';
    infoRequest.viewed = false;

    let url = spmsUrl + '/editor/story/' + story.id;
    let response = await fetch(url, {method: 'POST', body:JSON.stringify(infoRequest)});

    switch (response.status) {
        case 200: // success
            infoClose();
            getStories();
            break;
        default:
            alert('Something went wrong.');
            break;
    }
}

function requestInfo(storyId) {
    console.log("into function");
    let infoSection = document.getElementById("infoSection");
    infoSection.innerHTML += `
        <form class="form">
            <label for="outRequest">Info request:</label>
            <input name="outRequest" id="outRequest" type="text">
            <button type="button" class="button" onclick="sendInfoRequest(${storyId})">Send Request</button>
        </form>
    `;
}

async function approveStory(storyId) {
    let url = spmsUrl + '/editor/accept/' + storyId;
    let response = await fetch(url, {method:'PUT'});

    switch (response.status) {
        case 200: // success
            infoClose();
            getStories();
            break;
        default:
            alert('Something went wrong.');
            break;
    }
}

async function submitReject(storyId) {
    let rejection = {};
    rejection.rejectReason = document.getElementById("rejectReason").value;
    let url = spmsUrl + '/editor/reject/' + storyId;
    let response = await fetch(url, {method:'PUT', body:JSON.stringify(rejection)});

    switch (response.status) {
        case 200: // success
            infoClose();
            getStories();
            break;
        default:
            alert('Something went wrong.');
            break;
    }
}

function rejectStory(storyId) {
    let infoSection = document.getElementById("infoSection");
    infoSection.innerHTML += `
        <label for="rejectReason">Please Provide A Reason For Your Rejection Below</label><br>
        <input id="rejection" type="text" name="reasonForRejection" placeholder="Reason For Rejection Goes Here" required="required" /><br>
        <button type="button" class="button" onclick="submitReject(${storyId})">Reject Story</button>
        <button type="button" class="button" onclick="storyInfo(${storyId})>Cancel</button>
    `;
}

async function submitDraft(storyId) {
    let url = spmsUrl + '/author/draft/' + storyId;
    let draft = {};
    draft.draftText = document.getElementById("storyDraft").value;
    let response = await fetch(url, {method:'PUT', body:JSON.stringify(draft)});
    
    switch (response.status) {
        case 200: // success
            infoClose();
            getStories();
            break;
        default:
            alert('Something went wrong.');
            break;
    }
}

async function storyInfo(storyId) {
    let story= await getStoryById(storyId);
    let infoSection = document.getElementById("infoSection");
    document.getElementById("infoPopup").style.display = "block";
    console.log(story);
    
    infoSection.innerHTML = `
        <h3>Story Title: ${story.story.title}</h3><br>
        <p>Type: ${story.story.type.name}</p><br>
        <p>Genre: ${story.story.genre.name}</p><br>
        <p>Tagline: ${story.story.tagline}</p><br>
        <p>Description: ${story.story.description}</p><br>
        <p>Author Info: ${story.story.authorInfo}</p><br>
        <p>Completion Date: ${story.story.completionDate.month} ${story.story.completionDate.dayOfMonth}, ${story.story.completionDate.year}</p><br>
        <p>Status: ${story.status.name}</p><br>
        <p>Assistant Editor Status: ${story.asstStatus.name}</p><br>
        <p>Editor Status: ${story.editorStatus.name}</p><br>
        <p>Senior Editor Status: ${story.srStatus.name}</p><br>
    `;

    if (loggedInUser.role.id == 1) {
        switch (story.status.id) {
            case 4:
                infoSection.innerHTML += `
                    <h4>Unfortunately this story was rejected for the following reason: ${story.rejectReason}</h4><br>
                `;
            case 3:
                infoSection.innerHTML += `
                    <h4>Congratulations, this story was accepted by the editorial board! Please sumbit a draft ASAP</h4>
                    <input type="text" id="storyDraft" placeholder="Enter your draft here">
                    <button type="button" class="button" onclick="submitDraft(${storyId})">Submit draft</button>
                `;
        }
        infoSection.innerHTML += `
            <button type="button" class="button" onclick="deleteStory()">Cancel Story</button>
        `;
    } else {
        infoSection.innerHTML += `
            <button type="button" class="button" onclick="approveStory(${storyId})">Approve Story</button>
            <button type="button" class="button" onclick="rejectStory(${storyId})">Reject Story</button>
            <button type="button" class="button" onclick="requestInfo(${storyId})">Request Info</button>
        `;
    }

    infoSection.innerHTML += `
        <button type="button" class="button" onclick="infoClose()">Close</button>
    `;
}
