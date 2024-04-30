
import "./App.css";
import { BrowserRouter, Navigate, Route, Routes } from "react-router-dom";
import HeaderComponent from "./components/HeaderComponent";
import FooterComponent from "./components/FooterComponent";
import { isUserLoggedIn } from "./services/AuthService";


import LoginPage from "./components/Login";
import SearchForm from "./components/SearchForm";
import BookingForm from "./components/BookingForm";

function App() {
  function AuthenticatedRoute({ children }) {
    const isAuth = isUserLoggedIn();

    if (isAuth) {
      return children;
    }

    return <Navigate to="/" />;
  }

  return (
    <BrowserRouter>
      <Routes>
        <Route
          path="/"
          element={
            <>
              {/* <HeaderComponent /> */}
              {/* <Homepage /> */}
              <SearchForm />
              <FooterComponent />
            </>
          }
        ></Route>
        <Route path="/login" element={<LoginPage />}></Route>
        {/* <Route path="/book" element={<AuthenticatedRoute><BookingForm /></AuthenticatedRoute>}></Route> */}
      </Routes>
    </BrowserRouter>
        
        
  );
}

export default App;


// {/* <Routes>
//           <Route path="/" element={<LoginComponent />}></Route>
//           <Route
//             path="/todos"
//             element={
//               <AuthenticatedRoute>
//                 <ListTodoComponent />
//               </AuthenticatedRoute>
//             }
//           ></Route>
//           <Route
//             path="/add-todo"
//             element={
//               <AuthenticatedRoute>
//                 <TodoComponent />
//               </AuthenticatedRoute>
//             }
//           ></Route>
//           <Route
//             path="/update-todo/:id"
//             element={
//               <AuthenticatedRoute>
//                 <TodoComponent />
//               </AuthenticatedRoute>
//             }
//           ></Route>
//           <Route path="/login" element={<LoginComponent />}></Route>
//           <Route path="/register" element={<RegisterComponent />}></Route>
//         </Routes> */}