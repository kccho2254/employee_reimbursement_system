let apiURL = 'http://localhost:8080/projectOne/api/LoggedEmployee';
let nameId;

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
  document.getElementById('userData').innerHTML = ` ${user}`;
}

getData();


// employee form for viewing past requests
document.getElementById('tabs-1').innerHTML = `
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
    <tbody id='pastData'>
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

let arrTwo = [];
function populatePastReims(response) {
  let filteredRes = response.filter(function (n) {
    return (n.reimb_author === nameId && n.reimb_resolver != 0);
  });

  let filteredResTwo = filteredRes.filter(function(n) {

    return (n.reimb_resolver !== 0);
  })

  for (i = 0; i < filteredResTwo.length; i++) {
    let data = ` 
      <tr>
          <th scope="row">${i + 1}</th>
          <td>${filteredResTwo[i].reimb_submitted}</td>
          <td>${filteredResTwo[i].reimb_resolved}</td>
          <td>${filteredResTwo[i].reimb_desc}</td>
          <td>$${filteredResTwo[i].reimb_amount}</td>
          <td>${filteredResTwo[i].type}</td>
      </tr>
  `;

    let res = data.replaceAll(",", "/");
    arrTwo.push(res);
  }
  document.getElementById('pastData').innerHTML = arr;

  if (filteredResTwo.length === 0) {
    document.getElementById('pastData').innerHTML = `
      <tr>
          <td>0</td>
          <td>N/A</td>
          <td>No past requests found</td>
          <td>$0.00</td>
          <td>N/A</td>
          <td>N/A</td>
          <td>N/A</td>
      </tr>
    `;
  }
}

const viewReimsByEmp = 'http://localhost:8080/projectOne/api/Reimbursements';

function getPastReims() {

  let xhr = new XMLHttpRequest();
  xhr.onreadystatechange = receiveData;
  xhr.open('GET', `${viewReimsByEmp}`);
  xhr.send();

  function receiveData() {

    if (xhr.readyState === 4) {
      if (xhr.status >= 200 && xhr.status < 300) {

        let response = xhr.responseText;
        response = JSON.parse(response);
        populatePastReims(response);        
      } else {
        console.log('error retrieving json');
      }
    }
  }
}

// employee form for viewing their own pending requests
document.getElementById('tabs-2').innerHTML = `
<table class="table table-hover">
    <thead>
      <tr>
        <th scope="col">#</th>
        <th scope="col">Date created</th>
        <th scope="col">Description</th>
        <th scope="col">Reimbursement amount</th>
        <th scope="col">Type</th>
        <th scope="col">Status</th>
      </tr>
    </thead>
    <tbody id="arrData">
      <tr>
        <th scope="row">1</th>
        <td>5/1/2021</td>
        <td>Pens</td>
        <td>$20.00</td>
        <td>Other</td>
        </tr>
      <tr>
        <th scope="row">2</th>
        <td>3/3/2021</td>
        <td>Flight to business meeting, taxi fare</td>
        <td>$500.00</td>
        <td>Travel</td>
      </tr>

    </tbody>
  </table>

`;


function getPendingReims() {

  let xhr = new XMLHttpRequest();
  xhr.onreadystatechange = receiveData;
  xhr.open('GET', `${viewReimsByEmp}`);
  xhr.send();

  function receiveData() {

    if (xhr.readyState === 4) {
      if (xhr.status >= 200 && xhr.status < 300) {

        let response = xhr.responseText;
        response = JSON.parse(response);
        populatePendingReims(response);
      } else {
        console.log('error retrieving json');
      }
    }
  }
}

// middle tab, pending tickets
let arr = [];
function populatePendingReims(response) {

  console.log("This thing on?");
  let filteredRes = response.filter(function (n) {

    return (n.reimb_author === nameId);
  });
  console.log(filteredRes);

  let filteredResTwo = filteredRes.filter(function(n) {
    

    return (n.reimb_resolver === 0);
  })
  console.log(filteredResTwo);

  for (i = 0; i < filteredResTwo.length; i++) {
    let data = ` 
      <tr>
          <th scope="row">${i + 1}</th>
          <td>${filteredResTwo[i].reimb_submitted}</td>
          <td>${filteredResTwo[i].reimb_desc}</td>
          <td>$${filteredResTwo[i].reimb_amount}</td>
          <td>${filteredResTwo[i].type}</td>
          <td>${filteredResTwo[i].status}</td>
      </tr>
  `;

    let res = data.replaceAll(",", "/");
    arr.push(res);
  }
  document.getElementById('arrData').innerHTML = arr;

  if (filteredRes.length === 0) {
    document.getElementById('arrData').innerHTML = `
      <tr>
          <td>0</td>
          <td>N/A</td>
          <td>No pending requests found</td>
          <td>$0.00</td>
          <td>N/A</td>
      </tr>
    `;
  }
}

// employee form for submitting new reimbursement requests
document.getElementById('tabs-3').innerHTML = `
<form method="post" action="MakeNewReq" style="width: 100%; margin: 10px;">
  <div class="form-group">
    <label>Amount to reimburse</label>
    <input type="text" name="amount" class="form-control" placeholder="$0.00">
  </div>
  <div class="form-group">
    <label>Type</label>
    <select name="type" class="form-control">
      <option value="1">Lodging</option>
      <option value="2">Travel</option>
      <option value="3">Food</option>
      <option value="4">Other</option>
    </select>
  </div>
  <div class="form-group">
    <label for="exampleFormControlTextarea1">Description</label>
    <textarea class="form-control" name="description" rows="3"></textarea>
  </div>
  <input type="submit" value="Submit" style="background-color: #ff6000;  border-color:blanchedalmond;"
  class="btn btn-primary"></input>
  </form>
`;


let empTabs = Array.from(document.querySelectorAll('.li-tab'));

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
  document.getElementById(tabContentIdToShow).style.display = 'flex';
}

getPastReims();
getPendingReims();