let fetchUrl = 'http://localhost:7000/reimbursements';
let logoutUrl = 'http://localhost:7000/logout';
function getAllData() {
	

	fetch(fetchUrl).then(
		response => {
			response.json().then(
				json_body => {
					populateTable(json_body);
				}
			)
		}
	)
}
function AllReimbursements(data)
{
    let totalCount = parseInt(data['totalCount']);
	let totalValue = parseFloat(data['totalValue']).toFixed(4);
	let average;
	if(totalCount >= 0) {
		average = (totalValue / totalCount).toFixed(4);
	} else {
		average = (0).toFixed(4);
	}
	let maxUser = data['maxUser'];
	let maxAmount = parseFloat(data['maxAmount']).toFixed(4);
	let UserAverage = data['UserAverage'];
	let averageAmount = parseFloat(data['average']).toFixed(4);

    document.getElementById('value_column').innerHTML = '<tr><td>' + totalCount + ' ($' + totalValue + ')</td></tr>' +
    '<tr><td>$' + average + '</td></tr>' +
    '<tr><td>' + maxUser + ' ($' + maxAmount + ')</td></tr>' +
    '<tr><td>' + UserAverage + ' ($' + averageAmount + ')</td></tr>';

}