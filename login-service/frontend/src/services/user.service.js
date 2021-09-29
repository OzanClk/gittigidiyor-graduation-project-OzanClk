import axios from 'axios';
import authHeader from './auth-header';

const API_URL = 'http://localhost:8080/api/test/';

const USER_SERVICE_API_URL_GET = 'http://localhost:8082/api/get-customers';

const USER_SERVICE_API_URL_POST = 'http://localhost:8082/api/save-customers';

const USER_SERVICE_API_URL_DELETE = 'http://localhost:8082/api/delete-customers';

const CREDIT_APPLICATION_API_URL = 'http://localhost:8083/api/creditApplication';

const CREDIT_APPLICATION_API_URL_GET_BY_IDENT_NO = 'http://localhost:8083/api/get-customer-by-identification-number';


    class UserService {
        getPublicContent() {
            return axios.get(API_URL + 'all');
        }

        getUserBoard() {
            return axios.get(API_URL + 'user', {headers: authHeader()});
        }

        getModeratorBoard() {
            return axios.get(API_URL + 'mod', {headers: authHeader()});
        }

        getAdminBoard() {
            return axios.get(API_URL + 'admin', {headers: authHeader()});
        }

        getCustomers() {
            return axios.get(USER_SERVICE_API_URL_GET);
        }

        saveCustomers(customer) {
            return axios.post(USER_SERVICE_API_URL_POST, customer);
        }

        getCustomersById(customerId) {
            return axios.get(USER_SERVICE_API_URL_GET + '/' + customerId);
        }

        deleteCustomersById(customerId) {
            return axios.delete(USER_SERVICE_API_URL_DELETE + '/' + customerId);
        }

        applyCredit(creditApp) {
            console.log("BURADAN SONRA GİTME VAKTİ"+" "+JSON.stringify(creditApp));
            return axios.post(CREDIT_APPLICATION_API_URL, creditApp);
        }

        getCreditAppByIdentificationNumber(identificationNumber) {
            return axios.get(CREDIT_APPLICATION_API_URL_GET_BY_IDENT_NO + '/' + identificationNumber)
        }


    }

export default new UserService();
