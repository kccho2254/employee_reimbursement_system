let apiURL = 'http://localhost:8080/projectOne/api/LoggedManager';
let nameId;
let empTabs = Array.from(document.querySelectorAll('.li-tab'));

function getData() {
    let xhr = new XMLHttpRequest();
    xhr.onreadystatechange = receiveData;
    xhr.open('GET', `${apiURL}`);
    xhr.send();

    function receiveData() {

        if (xhr.readyState === 4) {
            if (xhr.status >= 200 && xhr.status < 300) {
                let response = xhr.responseText;
                response = JSON.parse(response);
                populateData(response);
            } else {
                console.log("Error getting data");
            }
        }
    }
}

function populateData(response) {

    let user = response.username;
    nameId = response.id;
    document.getElementById('userData').innerHTML = `Logged in as: ${user}`;
}

getData();

const handleClick = (e) => {
    e.preventDefault();
    empTabs.forEach(node => {
        node.classList.remove('active');
    });
    e.currentTarget.classList.add('active');
}

empTabs.forEach(node => {
    node.addEventListener('click', handleClick)
});


function showStuff(element) {
    var tabContents = document.getElementsByClassName('tabContent');
    for (var i = 0; i < tabContents.length; i++) {
        tabContents[i].style.display = 'none';
    }

    // change tabsX into tabs-X in order to find the correct tab content
    var tabContentIdToShow = element.id.replace(/(\d)/g, '-$1');
    document.getElementById(tabContentIdToShow).style.display = 'block';
}

// get requests by employee /////////////////////////////////////////////////////////////////////////////////////////////////
document.getElementById('tabs-1').innerHTML = `
<div style="padding:10px;" class="form-inline my-2 my-lg-0">
<input class="form-control mr-sm-2" type="search" placeholder="Search" id="clickme" aria-label="Search">
<button class="btn btn-outline-success my-2 my-sm-0" onclick="getReims()" type="submit">Search</button>
</div> 

    <table class="table table-hover">
    <thead>
      <tr>
        <th scope="col">#</th>
        <th scope="col">Date created</th>
        <th scope="col">Date resolved</th>
        <th scope="col">Description</th>
        <th scope="col">Reimbursement amount</th>
        <th scope="col">Type</th>
        <th scope="col">Status</th>
      </tr>
    </thead>
    <tbody id="empData">
      <tr>
        <th scope="row">0</th>
        <td>0/0/0000</td>
        <td>0/0/0000</td>
        <td>Enter an ID and click search. For a list of employees, navigate to the menu at the top and select the first option. The 'id' column in the populated list of users corresponds to the ID inputtable in the search bar here. The 'roleId' column specifies if the user is an employee (1) or manager (2). </td>
        <td>$0.00</td>
        <td>N/A</td>
        <td>N/A</td>
        </tr>
    </tbody>
  </table>
`;
const viewReimsByEmp = 'http://localhost:8080/projectOne/api/Reimbursements';

// this one will only fire when the search button is clicked
function getReims() {

    let xhr = new XMLHttpRequest();
    xhr.onreadystatechange = receiveData;
    xhr.open('GET', `${viewReimsByEmp}`);
    xhr.send();

    function receiveData() {

        if (xhr.readyState === 4) {
            if (xhr.status >= 200 && xhr.status < 300) {

                let response = xhr.responseText;
                response = JSON.parse(response);
                showRequestsByEmp(response);
            } else {
                console.log('error retrieving json');
            }
        }
    }
}

function showRequestsByEmp(response) {
    const empValue = document.getElementById('clickme').value;
    console.log(parseInt(empValue));

    let filteredArr = [];
    let filteredRes = response.filter(function (n) {

        return (n.reimb_author === parseInt(empValue));
    });

    for (i = 0; i < filteredRes.length; i++) {
        let data = ` 
            <tr>
                <th scope="row">${i + 1}</th>
                <td>${filteredRes[i].reimb_submitted}</td>
                <td>${filteredRes[i].reimb_resolved}</td>
                <td>${filteredRes[i].reimb_desc}</td>
                <td>$${filteredRes[i].reimb_amount}</td>
                <td>${filteredRes[i].type}</td>
                <td>${filteredRes[i].status}</td>
            </tr>
        `;

        let res = data.replaceAll(",", "/");
        filteredArr.push(res);
    }
    document.getElementById('empData').innerHTML = filteredArr;
}

// employee form for viewing their own pending requests ///////////////////////////////////////////////////////////////////
document.getElementById('tabs-2').innerHTML = `
<table class="table table-hover">
    <thead>
      <tr>
        <th scope="col">Request ID</th>
        <th scope="col">Author ID</th>
        <th scope="col">Date created</th>
        <th scope="col">Description</th>
        <th scope="col">Reimbursement amount</th>
        <th scope="col">Type</th>
        <th scope="col">Update</th>
      </tr>
    </thead>
    <tbody id="updateData">
      
    </tbody>
  </table>

`;

function showPendingRequestsUpdateable(response) {

    // this one needs to get all the rows that have resolver = 0
    let filteredArr = [];
    let filteredRes = response.filter(function (n) {
        return (n.reimb_resolver === 0);
    });

    for (i = 0; i < filteredRes.length; i++) {
        let data = ` 
            <tr>
                <td>${filteredRes[i].id}</td>
                <td>${filteredRes[i].reimb_author}</td>
                <td>${filteredRes[i].reimb_submitted}</td>
                <td>${filteredRes[i].reimb_desc}</td>
                <td>$${filteredRes[i].reimb_amount}</td>
                <td>${filteredRes[i].type}</td>
                <td>
                <form method="post" action="UpdateReq" style="width: 100%; display: flex;">
                <input type="hidden" name="reqID" value=${filteredRes[i].id}></input>

                <select style="width: 50%"; name="status" class="form-control">
                <option value="2">Accept</option>
                <option value="3">Decline</option>
                </select>

                <input type="submit" value="Submit" style="background-color: #ff6000;  border-color:blanchedalmond;"
                class="btn btn-primary"></input>
                </form>
                </td>
            </tr>
        `;

        data = data.replaceAll(",", "/");
        filteredArr.push(data);
    }
    document.getElementById('updateData').innerHTML = filteredArr;
}


function updateReims() {

    let xhr = new XMLHttpRequest();
    xhr.onreadystatechange = receiveData;
    xhr.open('GET', `${viewReimsByEmp}`);
    xhr.send();

    function receiveData() {

        if (xhr.readyState === 4) {
            if (xhr.status >= 200 && xhr.status < 300) {

                let response = xhr.responseText;
                response = JSON.parse(response);
                showPendingRequestsUpdateable(response);
            } else {
                console.log('error retrieving json');
            }
        }
    }
};

// view all resolved requests ////////////////////////////////////////////////////////////////////////////////////////////////
document.getElementById('tabs-3').innerHTML = `
    <table class="table table-hover">
    <thead>
      <tr>
        <th scope="col">#</th>
        <th scope="col">Date created</th>
        <th scope="col">Author</th>
        <th scope="col">Date resolved</th>
        <th scope="col">Resolver ID</th>
        <th scope="col">Description</th>
        <th scope="col">Reimbursement amount</th>
        <th scope="col">Type</th>
        <th scope="col">Status</th>
      </tr>
    </thead>
    <tbody id="viewResolvedData">
      <tr>
        <th scope="row">1</th>
        <td>5/1/2021</td>
        <td>5/4/2021</td>
        <td>Pens</td>
        <td>$20.00</td>
        <td>Other</td>
        <td>Accepted</td>
        </tr>
    </tbody>
  </table>
`;

function getResolvedReims() {

    let xhr = new XMLHttpRequest();
    xhr.onreadystatechange = receiveData;
    xhr.open('GET', `${viewReimsByEmp}`);
    xhr.send();

    function receiveData() {

        if (xhr.readyState === 4) {
            if (xhr.status >= 200 && xhr.status < 300) {

                let response = xhr.responseText;
                response = JSON.parse(response);
                showResolvedRequests(response);
            } else {
                console.log('error retrieving json');
            }
        }
    }
};

function showResolvedRequests(response) {

    // this one needs to get all the rows that have resolver = 0
    let filteredArr = [];
    let filteredRes = response.filter(function (n) {
        return (n.reimb_resolver !== 0);
    });

    for (i = 0; i < filteredRes.length; i++) {
        let data = ` 
            <tr>
                <th scope="row">${filteredRes[i].id}</th>
                <td>${filteredRes[i].reimb_submitted}</td>
                <td>${filteredRes[i].reimb_author}</td>
                <td>${filteredRes[i].reimb_resolved}</td>
                <td>${filteredRes[i].reimb_resolver}</td>
                <td>${filteredRes[i].reimb_desc}</td>
                <td>$${filteredRes[i].reimb_amount}</td>
                <td>${filteredRes[i].type}</td>
                <td>${filteredRes[i].status}</td>                
            </tr>
        `;
    
        data = data.replaceAll(",", "/");
        filteredArr.push(data);
    }
    document.getElementById('viewResolvedData').innerHTML = filteredArr;

    if (filteredRes.length === 0) {
        document.getElementById('viewResolvedData').innerHTML = `
          <tr>
              <td>0</td>
              <td>N/A</td>
              <td>No resolved requests found</td>
              <td>$0.00</td>
              <td>N/A</td>
          </tr>
        `;
    }
};

// this one will fire regardless on refreshing the page
