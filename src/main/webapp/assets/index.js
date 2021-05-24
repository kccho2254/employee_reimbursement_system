
// Sign up template form
document.getElementById('tabs-1').innerHTML = `

<div class="form-group">
<label for="email">Email address</label>
<input type="email" class="form-control" name="email" required="required" aria-describedby="emailHelp"
    placeholder="Enter email">
<small id="emailHelp" class="form-text text-muted">We'll never share your email with anyone
    else.</small>
</div>
<div class="form-group">
<label for="inputUsername">Username</label>
<input type="text" class="form-control" name="username" required="required" placeholder="Username">
</div>
<div class="form-group">
<label for="inputPassword">Password</label>
<input type="password" class="form-control" name="password" required="required" placeholder="Password">
</div>
<div class="form-group">
<label for="firstName">First Name</label>
<input type="text" class="form-control" name="firstName" required="required"  placeholder="John">
</div>
<div class="form-group">
<label for="lastName">Last Name</label>
<input type="text" class="form-control" name="lastName" required="required" placeholder="Finch">
</div>
<div class="form-group">
<label for="role">Position</label>
<select class="form-control" name="role" required="required" id="roleSelector">
    <option value="1">Employee</option>
    <option value="2">Manager</option>
</select>
</div>

<input type="submit" value="Submit" style="background-color: #ff6000;  border-color:blanchedalmond;"
class="btn btn-primary"></input>
`;

// login template form
document.getElementById('tabs-2').innerHTML = `
<div class="form-group" style="margin-top: 34px;">
<label for="emaillogin">Email address</label>
<input type="email" name="email" id="emaillogin"class="form-control" aria-describedby="emailHelp"
    placeholder="Enter email">
<small id="emailHelp" class="form-text text-muted">We'll never share your email with anyone
    else.</small>
</div>
<div class="form-group" style="margin-bottom: 34px;">
<label for="passwordlogin">Password</label>
<input type="password" name="password" id="passwordlogin" class="form-control" placeholder="Password">
</div>

<input type="submit" value="Submit" style="background-color: #ff6000;  border-color:blanchedalmond;"
class="btn btn-primary"></input>
`;

function showStuff(element) {
    var tabContents = document.getElementsByClassName('tabContent');
    for (var i = 0; i < tabContents.length; i++) {
        tabContents[i].style.display = 'none';
    }

    // change tabsX into tabs-X in order to find the correct tab content
    var tabContentIdToShow = element.id.replace(/(\d)/g, '-$1');
    document.getElementById(tabContentIdToShow).style.display = 'block';
}

