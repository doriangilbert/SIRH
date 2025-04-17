import React, { useState } from 'react'
import axios from 'axios'
import Header from './components/Header'
import DataTable from './components/DataTable'

const MainPage = ({ employeeId, setEmployeeId }) => {
    const [data, setData] = useState([])
    const [columns, setColumns] = useState([])
    const [error, setError] = useState(null)
    const [selectedClass, setSelectedClass] = useState('')
    const [relatedDetails, setRelatedDetails] = useState(null)
    const [showRelatedDetails, setShowRelatedDetails] = useState(false)

    const handleLogout = () => {
        localStorage.removeItem('employeeId')
        setEmployeeId('')
        alert('Successfully logged out.')
    }

    const fetchData = (selectedClass) => {
        setSelectedClass(selectedClass)
        setShowRelatedDetails(false)
        axios.get(`http://localhost:8080/${selectedClass}`)
            .then(response => {
                const sortedData = Array.isArray(response.data) ? response.data.sort((a, b) => a.id - b.id) : []
                setData(sortedData)
                if (sortedData.length > 0) {
                    setColumns(Object.keys(sortedData[0]))
                }
                setError(null)
            })
            .catch(error => {
                setError(error.response ? `Error: ${error.response.data.message}` : 'An error occurred while fetching data.')
            })
    }

    const handleRelatedIdClick = (relatedClass, id) => {
        const formattedClass = relatedClass.toLowerCase().endsWith('s') ? relatedClass : `${relatedClass}s`
        axios.get(`http://localhost:8080/${formattedClass}/${id}`)
            .then(response => {
                setRelatedDetails(response.data)
                setShowRelatedDetails(true)
                setError(null)
            })
            .catch(error => {
                setError(error.response ? `Error: ${error.response.data.message}` : 'An error occurred while fetching related details.')
            })
    }

    const renderCell = (value, column) => {
        if (['year', 'leaveBalance'].includes(column)) {
            return value
        }
        if (column !== 'id' && typeof value === 'number') {
            return (
                <button
                    onClick={() => handleRelatedIdClick(column, value)}
                    className="text-blue-500 underline"
                >
                    {value}
                </button>
            )
        }
        if (Array.isArray(value)) {
            return (
                <div>
                    {value.map((id, index) => (
                        <button
                            key={index}
                            onClick={() => handleRelatedIdClick(column, id)}
                            className="text-blue-500 underline mr-2"
                        >
                            {id}
                        </button>
                    ))}
                </div>
            )
        }
        if (typeof value === 'boolean') {
            return value ? 'True' : 'False'
        }
        return value
    }

    return (
        <>
            <Header fetchData={fetchData} handleInit={() => {}} />
            <div className="h-screen flex flex-col items-center">
                <h2 className="text-xl mb-4">Logged in with employee ID : {employeeId}</h2>
                <button
                    onClick={handleLogout}
                    className="bg-red-500 hover:bg-red-700 text-white font-bold py-2 px-4 rounded mb-4"
                >
                    Logout
                </button>
                {error && <div className="text-red-500 mb-4">{error}</div>}
                {!showRelatedDetails ? (
                    <>
                        {selectedClass && <h2 className="text-2xl mb-4">{selectedClass}</h2>}
                        <DataTable columns={columns} data={data} renderCell={renderCell} />
                    </>
                ) : (
                    <div className="p-4 border border-gray-400">
                        <button
                            onClick={() => setShowRelatedDetails(false)}
                            className="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded mb-4"
                        >
                            Retour
                        </button>
                        <h3 className="text-xl mb-2">Related details</h3>
                        <pre>{JSON.stringify(relatedDetails, null, 2)}</pre>
                    </div>
                )}
            </div>
        </>
    )
}

export default MainPage