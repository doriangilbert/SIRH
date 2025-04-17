import React, { useState } from 'react'

const DataTable = ({ data }) => {
    const [relatedDetails, setRelatedDetails] = useState(null)
    const [showRelatedDetails, setShowRelatedDetails] = useState(false)

    const handleRelatedIdClick = (relatedClass, id) => {
        const formattedClass = formatRouteName(relatedClass)
        fetch(`http://localhost:8080/${formattedClass}/${id}`)
            .then(response => response.json())
            .then(details => {
                setRelatedDetails(details)
                setShowRelatedDetails(true)
            })
            .catch(() => alert('Error while retrieving details.'))
    }

    const formatRouteName = (routeName) => {
        const lowerRouteName = routeName.toLowerCase()
        if (["manager", "reviewer"].includes(lowerRouteName)) {
            return "employees"
        }
        if (!lowerRouteName.endsWith('s')) {
            return lowerRouteName + 's'
        }
        return lowerRouteName
    }

    if (!data || data.length === 0) {
        return <div>No data available.</div>
    }

    const columns = Object.keys(data[0])

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
        <div>
            {!showRelatedDetails ? (
                <table className="table-auto border-collapse border border-gray-400 mt-5">
                    <thead>
                    <tr>
                        {columns.map(column => (
                            <th key={column} className="border border-gray-300 px-4 py-2">{column}</th>
                        ))}
                    </tr>
                    </thead>
                    <tbody>
                    {data.map(item => (
                        <tr key={item.id}>
                            {columns.map(column => (
                                <td key={column} className="border border-gray-300 px-4 py-2">
                                    {renderCell(item[column], column)}
                                </td>
                            ))}
                        </tr>
                    ))}
                    </tbody>
                </table>
            ) : (
                <div className="p-4 border border-gray-400">
                    <button
                        onClick={() => setShowRelatedDetails(false)}
                        className="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded mb-4"
                    >
                        Back
                    </button>
                    <h3 className="text-xl mb-2">Related details</h3>
                    <pre>{JSON.stringify(relatedDetails, null, 2)}</pre>
                </div>
            )}
        </div>
    )
}

export default DataTable