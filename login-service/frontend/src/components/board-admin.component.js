import React, {Component} from "react";

import UserService from "../services/user.service";
import EventBus from "../common/EventBus";
import axios from "axios";
import {Link} from "react-router-dom";
import {
    ListGroup,
    ListGroupItem,
    Button,
    Form,
    FormGroup,
    Label,
    Input,
    Table
} from "reactstrap";

export default class BoardAdmin extends Component {
    constructor(props) {
        super(props);

        this.state = {
            content: "",
            customers: []
        };

        this.addCustomer = this.addCustomer.bind(this);
        this.editCustomer = this.editCustomer.bind(this);
        this.deleteCustomer = this.deleteCustomer.bind(this);

    }
    deleteCustomer = (customerId) => {

        UserService.deleteCustomersById(customerId).then(res => {
            this.setState({customers: this.state.customers.filter(customer => customer.id !== customerId)})
        });


    }
    editCustomer(id) {
        this.props.history.push(`/update-customer/${id}`);
    }

    addCustomer() {
        this.props.history.push('/add-customer');
    }

    componentDidMount() {


        UserService.getCustomers().then((res) => {
            this.setState({customers: res.data})
            console.log(this.state.customers)
        })

        UserService.getAdminBoard().then(
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
        const {customers} = this.state;
        return (
            <div className="container">
                <header className="jumbotron">
                    <h3>Admin Portal</h3>
                </header>
                <div>
                    <h2 className="text center">Customers</h2>
                    <Button style={{position:'absolute',right:'450px',top:'275px'}} color="success" onClick={this.addCustomer}>Add Customer</Button>
                </div>
                <Table>
                    <thead>
                    <tr>
                        <th>#</th>
                        <th>First Name</th>
                        <th>Last Name</th>
                        <th>Email</th>
                        <th>PhoneNumber</th>
                        <th>Country</th>
                        <th>City</th>
                        <th>District</th>
                        <th></th>
                    </tr>
                    </thead>
                    <tbody>

                    {
                        this.state.customers.map(
                            customer =>
                                <tr key={customer.id}>
                                    <td>{customer.id}</td>
                                    <td>{customer.firstname}</td>
                                    <td>{customer.lastname}</td>
                                    <td>{customer.email}</td>
                                    <td>{customer.phoneNumber}</td>
                                    <td>{customer.address[0].country}</td>
                                    <td>{customer.address[0].city}</td>
                                    <td>{customer.address[0].district}</td>
                                    <td>
                                        <Button color="info"  onClick={() => this.editCustomer(customer.id)}>Update</Button>
                                        <Button colo="danger" onClick={() => this.deleteCustomer(customer.id)}>Delete</Button>
                                    </td>
                                </tr>
                        )
                    }
                    </tbody>
                </Table>

            </div>
        );
    }
}
