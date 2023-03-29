import React from 'react'
import { Admin, Resource} from 'react-admin'
import jsonServerProvider from 'ra-data-json-server'
import { UserList,UserEdit,UserCreate} from './Users';
import { authProvider } from './AuthProvider';

const dataProvider = jsonServerProvider('https://jsonplaceholder.typicode.com');

const App = ()=>(

      <Admin dataProvider={dataProvider} authProvider={authProvider}>
        <Resource name='users' list={UserList} edit={UserEdit} create={UserCreate}/>
      </Admin>

)
export default App;