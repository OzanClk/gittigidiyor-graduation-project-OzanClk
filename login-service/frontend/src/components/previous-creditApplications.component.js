import React, {Component} from 'react';
import UserService from "../services/user.service";
import EventBus from "../common/EventBus";
import {Button, Form, FormGroup, Label, Table} from "reactstrap";

class PreviousCreditApplicationsComponent extends Component {
    constructor(props) {
        super(props);

        this.state = {
            content: "",
            identificationNumber: this.props.match.params.identificationNumber,
            creditApplications: []
        };

    }


    async componentDidMount() {


        UserService.getCreditAppByIdentificationNumber(this.state.identificationNumber).then(response => {
            let creditApp = response.data;

            this.setState({
                creditApplications: creditApp
            });

            console.log(creditApp);
        });

        UserService.getUserBoard().then(
            response => {
                this.setState({
                    content: response.data
                });
            },
            error => {
                this.setState({
                    content:
                        (error.response &&
                            error.response.data &&
                            error.response.data.message) ||
                        error.message ||
                        error.toString()
                });

                if (error.response && error.response.status === 401) {
                    EventBus.dispatch("logout");
                }
            }
        );
    }

    render() {

        return (

            <div className="container">

                <Table>
                    <thead>
                    <tr>
                        <th>First Name</th>
                        <th>Last Name</th>
                        <th>Identification Number</th>
                        <th>PhoneNumber</th>
                        <th>Monthly Income</th>
                        <th>Credit Result</th>
                        <th>Credit Limit</th>
                        <th>Credit Application Date</th>
                    </tr>
                    </thead>
                    <tbody>

                    {
                        this.state.creditApplications.map(
                            creditApp =>
                                <tr key={creditApp.id}>
                                    <td>{creditApp.firstName}</td>
                                    <td>{creditApp.lastName}</td>
                                    <td>{creditApp.identificationNumber}</td>
                                    <td>{creditApp.phoneNumber}</td>
                                    <td>{creditApp.monthlyIncome}</td>
                                    <td>{creditApp.creditResult}</td>
                                    <td>{creditApp.creditLimit}</td>
                                    <td>{creditApp.creditApplicationDate}</td>
                                </tr>
                        )
                    }
                    </tbody>
                </Table>

            </div>
        );
    }
}

export default PreviousCreditApplicationsComponent;