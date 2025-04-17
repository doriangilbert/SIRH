import React, { useState } from 'react'
import axios from 'axios'
import DataTable from '../components/DataTable.jsx'
import Header from '../components/Header.jsx'

const DataPage = ({ employeeId, setEmployeeId, setCurrentPage }) => {
    const [data, setData] = useState([])
    const [error, setError] = useState(null)
    const [selectedClass, setSelectedClass] = useState('')

    const fetchData = (selectedClass) => {
        setSelectedClass(selectedClass)
        axios.get(`http://localhost:8080/${selectedClass}`)
            .then(response => {
                setData(response.data)
                setError(null)
            })
            .catch(error => {
                setError(error.response ? `Error: ${error.response.data.message}` : 'An error occurred while fetching data.')
            })
    }

    return (
        <div className="h-screen flex flex-col">
            <Header employeeId={employeeId} setEmployeeId={setEmployeeId} setCurrentPage={setCurrentPage} />
            <div className="flex flex-col items-center flex-grow">
                <div className="mb-4">
                    <label htmlFor="entity-select" className="mr-2">Select an entity:</label>
                    <select
                        id="entity-select"
                        onChange={(e) => fetchData(e.target.value)}
                        className="border border-gray-300 rounded px-2 py-1"
                    >
                        <option value="">-- Select --</option>
                        {['employees', 'evaluations', 'feedbacks', 'leaverequests', 'objectives', 'positions', 'skills', 'teams', 'trainings', 'trainingrequests', 'notifications'].map((item) => (
                            <option key={item} value={item}>
                                {item.charAt(0).toUpperCase() + item.slice(1)}
                            </option>
                        ))}
                    </select>
                </div>
                {error && <div className="text-red-500 mb-4">{error}</div>}
                {selectedClass && (
                    <DataTable data={data} />
                )}
            </div>
        </div>
    )
}

export default DataPage