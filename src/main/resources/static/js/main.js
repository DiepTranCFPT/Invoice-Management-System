// Main JavaScript file for Invoice Management System

$(document).ready(function() {
    // Initialize tooltips
    var tooltipTriggerList = [].slice.call(document.querySelectorAll('[data-bs-toggle="tooltip"]'));
    var tooltipList = tooltipTriggerList.map(function (tooltipTriggerEl) {
        return new bootstrap.Tooltip(tooltipTriggerEl);
    });
    
    // Auto-hide alerts after 5 seconds
    setTimeout(function() {
        $('.alert').alert('close');
    }, 5000);
    
    // Confirm delete actions
    $('.btn-delete').on('click', function(e) {
        e.preventDefault();
        if (confirm('Are you sure you want to delete this item?')) {
            window.location.href = $(this).attr('href');
        }
    });
    
    // AJAX form submission helper
    window.submitAjaxForm = function(url, data, successCallback, errorCallback) {
        $.ajax({
            url: url,
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(data),
            success: function(response) {
                if (successCallback) {
                    successCallback(response);
                } else {
                    showSuccess('Operation completed successfully!');
                }
            },
            error: function(xhr) {
                if (errorCallback) {
                    errorCallback(xhr);
                } else {
                    const response = JSON.parse(xhr.responseText);
                    showError(response.message || 'Operation failed');
                }
            }
        });
    };
    
    // Show success message
    window.showSuccess = function(message) {
        showAlert(message, 'success');
    };
    
    // Show error message
    window.showError = function(message) {
        showAlert(message, 'danger');
    };
    
    // Show alert helper
    function showAlert(message, type) {
        const alertHtml = `
            <div class="alert alert-${type} alert-dismissible fade show" role="alert">
                ${message}
                <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
            </div>
        `;
        
        if ($('#alerts-container').length) {
            $('#alerts-container').html(alertHtml);
        } else {
            $('main').prepend('<div id="alerts-container">' + alertHtml + '</div>');
        }
        
        // Auto-hide after 5 seconds
        setTimeout(function() {
            $('.alert').alert('close');
        }, 5000);
    }
    
    // Format currency
    window.formatCurrency = function(amount) {
        return new Intl.NumberFormat('vi-VN', {
            style: 'currency',
            currency: 'VND'
        }).format(amount);
    };
    
    // Format date
    window.formatDate = function(dateString) {
        const date = new Date(dateString);
        return date.toLocaleDateString('vi-VN', {
            year: 'numeric',
            month: '2-digit',
            day: '2-digit',
            hour: '2-digit',
            minute: '2-digit'
        });
    };
});

// Agent management functions
window.approveAgent = function(agentId) {
    if (confirm('Are you sure you want to approve this agent?')) {
        $.post(`/admin/agents/${agentId}/approve`)
            .done(function() {
                showSuccess('Agent approved successfully!');
                setTimeout(() => location.reload(), 1000);
            })
            .fail(function() {
                showError('Failed to approve agent');
            });
    }
};

window.rejectAgent = function(agentId) {
    if (confirm('Are you sure you want to reject this agent?')) {
        $.post(`/admin/agents/${agentId}/reject`)
            .done(function() {
                showSuccess('Agent rejected successfully!');
                setTimeout(() => location.reload(), 1000);
            })
            .fail(function() {
                showError('Failed to reject agent');
            });
    }
};

window.activateAgent = function(agentId) {
    $.post(`/admin/agents/${agentId}/activate`)
        .done(function() {
            showSuccess('Agent activated successfully!');
            setTimeout(() => location.reload(), 1000);
        })
        .fail(function() {
            showError('Failed to activate agent');
        });
};

window.deactivateAgent = function(agentId) {
    if (confirm('Are you sure you want to deactivate this agent?')) {
        $.post(`/admin/agents/${agentId}/deactivate`)
            .done(function() {
                showSuccess('Agent deactivated successfully!');
                setTimeout(() => location.reload(), 1000);
            })
            .fail(function() {
                showError('Failed to deactivate agent');
            });
    }
};