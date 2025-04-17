import React from 'react'
import Header from './components/Header'

const MainPage = ({ employeeId, setEmployeeId, setCurrentPage }) => {
    return (
        <div className="h-screen flex flex-col">
            <Header employeeId={employeeId} setEmployeeId={setEmployeeId} setCurrentPage={setCurrentPage} />
            <div className="flex flex-col items-center justify-center flex-grow">
                <h1 className="text-3xl mb-6">Welcome</h1>
                <button
                    onClick={() => setCurrentPage('data')}
                    className="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded mb-4"
                >
                    See data
                </button>
            </div>
        </div>
    )
}

export default MainPage