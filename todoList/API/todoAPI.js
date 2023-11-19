//*********************************************************/
//                      START OF API 
//*********************************************************/


const API_URL = 'http://127.0.0.1:4000'

//**************************************************
//            request to sign in and sign up
//**************************************************/

const SIGN_IN =
  'mutation($username:String!, $password:String!){signIn(username:$username, password:$password)}'

const SIGN_UP =
  'mutation($username:String!, $password:String!){signUp(username:$username, password:$password)}'

//**************************************************
//            request to get a user
//**************************************************/

const GET_USER =
  `query($id:ID!){
      users(where: { id: $id }){
          id
          username
          password
      }
  }`

//**************************************************
//            request to get all tasks
//**************************************************/

const GET_TASKS =
`
query($username: String!) {
  tasks(where: { owner: {username : $username}}){
    id
    title
    done
  }
}
`
//**************************************************
//            request to get all users
//**************************************************/

const GET_USERS =
  `query{
      users{
          id
          username
          roles
      }
  }`

//**************************************************
//            request to create a task
//**************************************************/

const CREATE_TASK =
  `mutation($username: String!, $title: String!) {
    createTasks(input: { title: $title, done: false, owner: {connect : {where : {username : $username}}} }) {
      tasks {
        id
        title
        done
      }
    }
  }
  `
//**************************************************
//            request to delete a user
//**************************************************/

const DELETE_USER =
  `mutation($username:String!){
      deleteUsers(
          where: { username: $username }
      ){
        nodesDeleted
        relationshipsDeleted
        
      }
  }`

//**************************************************
//            request to delete a task
//**************************************************/

const DELETE_TASKS =
`mutation($username: String!, $id: ID!) {
  deleteTasks(where: { AND: { owner: { username: $username }, id: $id } }) {
    nodesDeleted
  }
}`

//**************************************************
//            request to update tasks
//**************************************************/

const UPDATE_TASKS =
`mutation($username: String!, $id: ID!, $done: Boolean) {
  updateTasks(
    where: { AND: { owner: { username: $username } }, id: $id }
    update: { done: $done }
  ) {
    tasks {
      id
      title
      done
    }
  }
}`

//*****************************************************************
//            request to mark all tasks as done or not done
//*****************************************************************/

const CHECK_NOT_CHECK = 

`mutation ($username: String!, $done: Boolean) {
  updateTasks(
    where: { owner: { username: $username } }
    update: { done: $done }
  ) {
    tasks {
      id
      title
      done
    }
  }
}`


//**************************************************
//            function to get all users
//**************************************************/

/**
 * @author: <a href="mailto:sdoumbouya633@gmail.com"> Sekou Doumbouya </a> 
 * @author: <a href="mailto:gloratty@gmail.com"> Abla Catherine Atty </a>
 * @version 1.0.0
 * @param {token} token
 * @throws error if the user is not found in the database 
 * @returns the list of all users
 */

export function getUsers (token) {
  return fetch(API_URL, {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
      'Authorization': 'Bearer ' + token
    },
    body: JSON.stringify({
      query: GET_USERS
    })
  })
    .then(response => {
      return response.json()
    })
    .then(jsonResponse => {
      if (jsonResponse.errors != null) {
        throw jsonResponse.errors[0]
      }
      return jsonResponse.data.users
    })
    .catch(error => {
      throw error
    })
}

//**************************************************
//            function to delete a user
//**************************************************/

/**
 * @author: <a href="mailto:sdoumbouya633@gmail.com"> Sekou Doumbouya </a> 
 * @author: <a href="mailto:gloratty@gmail.com"> Abla Catherine Atty </a>
 * 
 * @version 1.0.0
 * @param {token} token
 * @param {username} username
 * @throws error if the user is not found in the database  
 * @returns the id of user deleted
 */

export function deleteUser (username, token) {
  return fetch(API_URL, {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
      "Authorization": "Bearer " + token
    },
    body: JSON.stringify({
      query: DELETE_USER,
      variables: {
        username: username
      }
    })
  })
    .then(response => {
      return response.json()
    })
    .then(jsonResponse => {
      if (jsonResponse.errors != null) {
        throw jsonResponse.errors[0]
      }
      return jsonResponse.data
    })
    .catch(error => {
      throw error
    })
}


//**************************************************
//            function to sign in
//**************************************************/

/**
 * @author: <a href="mailto:sdoumbouya633@gmail.com"> Sekou Doumbouya </a> 
 * @author: <a href="mailto:gloratty@gmail.com"> Abla Catherine Atty </a>
 * 
 * @version 1.0.0
 * @param {username} username
 * @param {password} password 
 * @throws error if the username or password is wrong or if the user is not found in the database 
 * @returns the token
 */

export function signIn (username, password) {
  return fetch(API_URL, {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json'
    },
    body: JSON.stringify({
      query: SIGN_IN,
      variables: {
        username: username,
        password: password
      }
    })
  })
    .then(response => {
      return response.json()
    })
    .then(jsonResponse => {
      if (jsonResponse.errors != null) {
        throw jsonResponse.errors[0]
      }
      return jsonResponse.data.signIn
    })
    .catch(error => {
      throw error
    })
}

//**************************************************
//            function to sign up
//**************************************************/

/**
 * @author: <a href="mailto:sdoumbouya633@gmail.com"> Sekou Doumbouya </a> 
 * @author: <a href="mailto:gloratty@gmail.com"> Abla Catherine Atty </a>
 * 
 * @version 1.0.0
 * @param {username} username
 * @param {password} password
 * @throws error if the passwords are not matching  
 * @returns the token
 */
export function signUp (username, password) {
  return fetch(API_URL, {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json'
    },
    body: JSON.stringify({
      query: SIGN_UP,
      variables: {
        username: username,
        password: password
      }
    })
  })
    .then(response => {
      return response.json()
    })
    .then(jsonResponse => {
      if (jsonResponse.errors != null) {
        throw jsonResponse.errors[0]
      }
      return jsonResponse.data.signUp
    })
    .catch(error => {
      throw error
    })
}

//**************************************************
//            function to get all tasks
//**************************************************/

/**
 * @author: <a href="mailto:sdoumbouya633@gmail.com"> Sekou Doumbouya </a> 
 * @author: <a href="mailto:gloratty@gmail.com"> Abla Catherine Atty </a>
 * 
 * @version 1.0.0
 * @param {username} username
 * @param {token} token
 * @throws error if the user is not found in the database
 * @returns the list of all tasks
 */

export function getTasks(username, token){
  return fetch(API_URL, {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
      'Authorization': 'Bearer ' + token
    },
    body: JSON.stringify({
      query: GET_TASKS,
      variables: {
        username: username
      }
    })
  })
    .then(response => {
      return response.json()
    })
    .then(jsonResponse => {
      if (jsonResponse.errors != null) {
        throw jsonResponse.errors[0]
      }
      return jsonResponse.data.tasks
    })
    .catch(error => {
      throw error
    })
    
}

//**************************************************
//            function to create a task
//**************************************************/

/**
 * @author: <a href="mailto:sdoumbouya633@gmail.com"> Sekou Doumbouya </a> 
 * @author: <a href="mailto:gloratty@gmail.com"> Abla Catherine Atty </a>
 * 
 * @version 1.0.0
 * @param {username} username
 * @param {task} task
 * @param {token} token
 * @throws error if the user is not found in the database 
 * @returns the task created
 */

export function createTask(username, task, token){
  return fetch(API_URL, {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
      'Authorization': 'Bearer ' + token,
    },
    body: JSON.stringify({
      query: CREATE_TASK,
      variables: {
        username: username,
        title: task
      }
    })
  })
    .then(response => {
      return response.json()
    }
    )
    .then(jsonResponse => {
      if (jsonResponse.errors != null) {
        throw jsonResponse.errors[0]
      }
      return jsonResponse.data.createTasks.tasks[0]
    })
    .catch(error => {
      throw error
    })
}

//**************************************************
//            function to delete a task
//**************************************************/

/**
 * @author: <a href="mailto:sdoumbouya633@gmail.com"> Sekou Doumbouya </a> 
 * @author: <a href="mailto:gloratty@gmail.com"> Abla Catherine Atty </a>
 *
 * @version 1.0.0
 * @param {username} username
 * @param {id} id
 * @param {token} token
 * @throws error if the user is not found in the database
 * @returns the task deleted
 * 
 */

export function deleteTasks(username, id, token){
  return fetch(API_URL, {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
      'Authorization': 'Bearer ' + token,
    },
    body: JSON.stringify({
      query: DELETE_TASKS,
      variables: {
        username: username,
        id: id
      }
    })
  })
    .then(response => {
      return response.json()
    })
    .then(jsonResponse => {
      if (jsonResponse.errors != null) {
        throw jsonResponse.errors[0]
      }
      return jsonResponse.data
    })
    .catch(error => {
      throw error
    })
}

//**************************************************
//            function to update a task
//**************************************************/

/**
 * @author: <a href="mailto:sdoumbouya633@gmail.com"> Sekou Doumbouya </a> 
 * @author: <a href="mailto:gloratty@gmail.com"> Abla Catherine Atty </a>
 * 
 * @version 1.0.0
 * @param {username} username
 * @param {id} id
 * @param {done} done
 * @param {token} token
 * @throws error if the user is not found in the database
 * @returns the task updated
 */

export function updateTasks(username, id, done, token){
  return fetch(API_URL, {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
      'Authorization': 'Bearer ' + token,
    },
    body: JSON.stringify({
      query: UPDATE_TASKS,
      variables: {
        username: username,
        id: id,
        done: done
      }
    })
  })
    .then(response => {
      return response.json()
    }) 
    .then(jsonResponse => {
      if (jsonResponse.errors != null) {
        throw jsonResponse.errors[0]
      }
      return jsonResponse.data.updateTasks.tasks[0]
    })
    .catch(error => {
      throw error
    })
}

//**************************************************
//            function to mark a task
//**************************************************/

/**
 * @author: <a href="mailto:sdoumbouya633@gmail.com"> Sekou Doumbouya </a> 
 * @author: <a href="mailto:gloratty@gmail.com"> Abla Catherine Atty </a>
 * 
 * @version 1.0.0
 * @param {username} username
 * @param {done} done
 * @param {token} token
 * @throws error if the user is not found in the database
 * @returns the task marked or unmarked 
 * 
 */

export function markTask(username, done, token) {
  return fetch(API_URL, {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
      'Authorization': 'Bearer ' + token,
    },
    body: JSON.stringify({
      query: CHECK_NOT_CHECK,
      variables: {
        username: username,
        done: done
      }
    })
  })
    .then(response => {
      return response.json()
    })
    .then(jsonResponse => {
      if (jsonResponse.errors != null) {
        throw jsonResponse.errors[0]
      }
      return jsonResponse.data.updateTasks.tasks
    })
    .catch(error => {
      throw error
    })
}

//*********************************************************/
//                      END OF API  
//*********************************************************/



